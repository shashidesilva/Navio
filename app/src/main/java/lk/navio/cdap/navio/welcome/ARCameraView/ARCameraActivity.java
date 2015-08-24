package lk.navio.cdap.navio.welcome.ARCameraView;

import android.location.Location;
import android.location.LocationListener;
import android.opengl.GLES20;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import lk.navio.cdap.navio.welcome.R;


public abstract class ARCameraActivity extends ActionBarActivity implements ArchitectViewHolderInterface {

    protected ArchitectView architectView;

    protected ArchitectView.SensorAccuracyChangeListener sensorAccuracyListener;

    protected Location lastKnownLocation;

    protected ILocationProvider locationProvider;

    protected LocationListener locationListener;

    protected ArchitectView.ArchitectUrlListener urlListener;

    protected JSONArray poiData;

    protected boolean isLoading = false;

    PlacesDataJson pdj = new PlacesDataJson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getContentViewId());

        this.architectView = (ArchitectView) findViewById(this.getArchitectViewId());

        final StartupConfiguration config = new StartupConfiguration(this.getWikitudeSDKLicenseKey(), this.getFeatures(), this.getCameraPosition());

        try {
            this.architectView.onCreate(config);
        } catch (RuntimeException r) {
            this.architectView = null;
            Toast.makeText(getApplicationContext(), "cannot create Architect View", Toast.LENGTH_SHORT).show();
        }

        this.sensorAccuracyListener = this.getSensorAccuracyListener();

        this.urlListener = this.getUrlListener();

        if (this.urlListener != null && this.architectView != null) {
            this.architectView.registerUrlListener(this.getUrlListener());
        }


        if (hasGeo()) {
            // listener passed over to locationProvider, any location update is handled here
            this.locationListener = new LocationListener() {

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }

                @Override
                public void onLocationChanged(final Location location) {
                    // forward location updates fired by LocationProvider to architectView, you can set lat/lon from any location-strategy
                    if (location != null) {
                        // sore last location as member, in case it is needed somewhere (in e.g. your adjusted project)
                        ARCameraActivity.this.lastKnownLocation = location;
                        if (ARCameraActivity.this.architectView != null) {
                            // check if location has altitude at certain accuracy level & call right architect method (the one with altitude information)
                            if (location.hasAltitude() && location.hasAccuracy() && location.getAccuracy() < 7) {
                                ARCameraActivity.this.architectView.setLocation(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy());
                            } else {
                                ARCameraActivity.this.architectView.setLocation(location.getLatitude(), location.getLongitude(), location.hasAccuracy() ? location.getAccuracy() : 1000);
                            }
                        }
                    }
                }
            };

            // locationProvider used to fetch user position
            this.locationProvider = getLocationProvider(this.locationListener);
        } else {
            this.locationProvider = null;
            this.locationListener = null;
        }

    }

    protected abstract StartupConfiguration.CameraPosition getCameraPosition();

    private int getFeatures() {
        int features = (hasGeo() ? StartupConfiguration.Features.Geo : 0) | (hasIR() ? StartupConfiguration.Features.Tracking2D : 0);
        return features;
    }

    protected abstract boolean hasGeo();
    protected abstract boolean hasIR();


    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (this.architectView != null) {

            // call mandatory live-cycle method of architectView
            this.architectView.onPostCreate();

            try {
                // load content via url in architectView, ensure '<script src="architect://architect.js"></script>' is part of this HTML file, have a look at wikitude.com's developer section for API references
                this.architectView.load(this.getARchitectWorldPath());

                if (this.getInitialCullingDistanceMeters() != ArchitectViewHolderInterface.CULLING_DISTANCE_DEFAULT_METERS) {
                    // set the culling distance - meaning: the maximum distance to render geo-content
                    this.architectView.setCullingDistance( this.getInitialCullingDistanceMeters() );
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // call mandatory live-cycle method of architectView
        if ( this.architectView != null ) {
            this.architectView.onResume();

            // register accuracy listener in architectView, if set
            if (this.sensorAccuracyListener!=null) {
                this.architectView.registerSensorAccuracyChangeListener( this.sensorAccuracyListener );
            }
        }

        // tell locationProvider to resume, usually location is then (again) fetched, so the GPS indicator appears in status bar
        if ( this.locationProvider != null ) {
            this.locationProvider.onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        // call mandatory live-cycle method of architectView
        if ( this.architectView != null ) {
            this.architectView.onPause();

            // unregister accuracy listener in architectView, if set
            if ( this.sensorAccuracyListener != null ) {
                this.architectView.unregisterSensorAccuracyChangeListener( this.sensorAccuracyListener );
            }
        }

        // tell locationProvider to pause, usually location is then no longer fetched, so the GPS indicator disappears in status bar
        if ( this.locationProvider != null ) {
            this.locationProvider.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // call mandatory live-cycle method of architectView
        if (this.architectView != null) {
            this.architectView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (this.architectView != null) {
            this.architectView.onLowMemory();
        }
    }


    @Override
    public abstract String getARchitectWorldPath();

    @Override
    public abstract ArchitectView.ArchitectUrlListener getUrlListener();
    @Override
    public abstract int getContentViewId();

    @Override
    public abstract String getWikitudeSDKLicenseKey();

    @Override
    public abstract int getArchitectViewId();

    @Override
    public abstract ILocationProvider getLocationProvider(final LocationListener locationListener);

    @Override
    public abstract ArchitectView.SensorAccuracyChangeListener getSensorAccuracyListener();

    @Override
    public abstract float getInitialCullingDistanceMeters();



    /**
     * helper to check if video-drawables are supported by this device. recommended to check before launching ARchitect Worlds with videodrawables
     * @return true if AR.VideoDrawables are supported, false if fallback rendering would apply (= show video fullscreen)
     */
    public static final boolean isVideoDrawablesSupported() {
        String extensions = GLES20.glGetString(GLES20.GL_EXTENSIONS);
        return extensions != null && extensions.contains( "GL_OES_EGL_image_external" ) && android.os.Build.VERSION.SDK_INT >= 14 ;
    }

    protected void injectData() {


        if (!isLoading) {
            final Thread t = new Thread(new Runnable() {

                @Override
                public void run() {

                    isLoading = true;

                    final int WAIT_FOR_LOCATION_STEP_MS = 2000;

                    while (lastKnownLocation==null && !isFinishing()) {

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(ARCameraActivity.this, "Fetching the location", Toast.LENGTH_SHORT).show();
                            }
                        });

                        try {
                            Thread.sleep(WAIT_FOR_LOCATION_STEP_MS);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }

                    if (lastKnownLocation!=null && !isFinishing()) {
                        // TODO: you may replace this dummy implementation and instead load POI information e.g. from your database
                        //poiData = getPoiInformation(lastKnownLocation, 20);
                        poiData = pdj.getJsonPOIData();
                        callJavaScript("World.loadPoisFromJsonData", new String[] { poiData.toString() });
                    }

                    isLoading = false;
                }
            });
            t.start();
        }
    }

    /**
     * call JacaScript in architectView
     * @param methodName
     * @param arguments
     */
    private void callJavaScript(final String methodName, final String[] arguments) {
        final StringBuilder argumentsString = new StringBuilder("");
        for (int i= 0; i<arguments.length; i++) {
            argumentsString.append(arguments[i]);
            if (i<arguments.length-1) {
                argumentsString.append(", ");
            }
        }

        if (this.architectView!=null) {
            final String js = ( methodName + "( " + argumentsString.toString() + " );" );
            this.architectView.callJavascript(js);
        }
    }

    /**
     * loads poiInformation and returns them as JSONArray. Ensure attributeNames of JSON POIs are well known in JavaScript, so you can parse them easily
     * @param userLocation the location of the user
     * @param numberOfPlaces number of places to load (at max)
     * @return POI information in JSONArray
     */
    public static JSONArray getPoiInformation(final Location userLocation, final int numberOfPlaces) {

        if (userLocation==null) {
            return null;
        }

        final JSONArray pois = new JSONArray();

        // ensure these attributes are also used in JavaScript when extracting POI data
        final String ATTR_ID = "id";
        final String ATTR_NAME = "name";
        final String ATTR_DESCRIPTION = "description";
        final String ATTR_LATITUDE = "latitude";
        final String ATTR_LONGITUDE = "longitude";
        final String ATTR_ALTITUDE = "altitude";

        for (int i=1;i <= numberOfPlaces; i++) {
            final HashMap<String, String> poiInformation = new HashMap<String, String>();
            poiInformation.put(ATTR_ID, String.valueOf(i));
            poiInformation.put(ATTR_NAME, "POI#" + i);
            poiInformation.put(ATTR_DESCRIPTION, "This is the description of POI#" + i);
            double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
            poiInformation.put(ATTR_LATITUDE, String.valueOf(poiLocationLatLon[0]));
            poiInformation.put(ATTR_LONGITUDE, String.valueOf(poiLocationLatLon[1]));
            final float UNKNOWN_ALTITUDE = -32768f;  // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
            // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
            poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
            pois.put(new JSONObject(poiInformation));
        }

        return pois;
    }

    /**
     * helper for creation of dummy places.
     * @param lat center latitude
     * @param lon center longitude
     * @return lat/lon values in given position's vicinity
     */
    private static double[] getRandomLatLonNearby(final double lat, final double lon) {
        return new double[] { lat + Math.random()/5-0.1 , lon + Math.random()/5-0.1};
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arcamera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
