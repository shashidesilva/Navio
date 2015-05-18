package lk.navio.cdap.navio.welcome.ARCameraView;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import org.json.JSONArray;

import java.io.IOException;

import lk.navio.cdap.navio.welcome.R;


public abstract class ARCameraActivity extends ActionBarActivity implements ArchitectViewHolderInterface {

    protected ArchitectView architectView;
    protected static final String SDK_KEY = "uPuJ6WGapvnis4F0vzcuKREuED2USrwYSz1zw4HkboOZWd1xBwVk47eBVZ8r1CUAVCl+7sB1dfEi9Goms6mIOQqqSPIQI2oVwDqxbI358bf5Lgn/ABiYvZ8wp62PquYeJVyvSrL/GMjBNn3ynss7zwy6VjcbYIhRzwdoVgiEKWVTYWx0ZWRfX2dEDoXe49j6GM8lJgiJzejQ3CpS5J8HcxhQjeQfBAxmrWnQRPeMgkxUV0xZjiH/69mzavd2ViilubDtwvtcRNRiWEelNwrkSHADiS73HeW5jBMEzUq1teHM04AVKygr5n46qlgkOrOGvQvT0fRAXzeI276CZtF0UDnFRs48aBYNeHcs4CCkcaMF63E77kTr0TZk8j6o2MdWdaLotiOYkXH7IE3mPVbKdXcm8Uypzol43gV9zvHUuwCv60ZCxzhLaS3vBIWPnREYUt21bMVXfHG/9wz1rIPZxrnx6y+cwRVoowqE2gOC8ePuXS7098fQDDKGglvyeN59jVWjKnqOIfO7znY9whcr76a1D6vOrRtS3aQGRfnTdTRqbGhuOiqjiQPli9ZSiWMHREFyaDn8dI0A1cPFEPFKtmhli3SHaMhQl6B4bChxey/1KABLHLagK6Nj7bTMk4F+45K2uk8GJuLvfLHhnNMSMRVbnAS/5wdSXHLsQa8lwck=";

    protected ArchitectView.SensorAccuracyChangeListener sensorAccuracyListener;

    protected Location lastKnownLocation;

    protected ILocationProvider locationProvider;

    protected LocationListener locationListener;

    protected ArchitectView.ArchitectUrlListener urlListener;

    protected JSONArray poiData;

    protected boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcamera);

        this.architectView = (ArchitectView) findViewById(R.id.architectView);

        final StartupConfiguration config = new StartupConfiguration(SDK_KEY);

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
                this.architectView.load("ARWorld.html");

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
        System.exit(0);
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
    public String getARchitectWorldPath() {
        return null;
    }

    @Override
    public ArchitectView.ArchitectUrlListener getUrlListener() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public String getWikitudeSDKLicenseKey() {
        return null;
    }

    @Override
    public int getArchitectViewId() {
        return 0;
    }

    @Override
    public ILocationProvider getLocationProvider(LocationListener locationListener) {
        return null;
    }

    @Override
    public ArchitectView.SensorAccuracyChangeListener getSensorAccuracyListener() {
        return null;
    }

    @Override
    public float getInitialCullingDistanceMeters() {
        return 0;
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
