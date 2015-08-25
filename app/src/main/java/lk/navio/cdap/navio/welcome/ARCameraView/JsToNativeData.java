package lk.navio.cdap.navio.welcome.ARCameraView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.view.Display;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lk.navio.cdap.navio.welcome.R;

public class JsToNativeData extends FragmentActivity {

    private GoogleMap mMap;

    public static final String EXTRAS_KEY_POI_ID = "id";
    public static final String EXTRAS_KEY_POI_TITILE = "title";
    public static final String EXTRAS_KEY_POI_DESCR = "description";
    public static final String EXTRAS_KEY_POI_LATITUDE = "latitude";
    public static final String EXTRAS_KEY_POI_LONGITUDE = "longitude";

    public static double end_latitude, end_longitude;
    public static String end_title, end_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        /*((TextView)findViewById(R.id.poi_id)).setText(  getIntent().getExtras().getString(EXTRAS_KEY_POI_ID) );
        ((TextView)findViewById(R.id.poi_title)).setText( getIntent().getExtras().getString(EXTRAS_KEY_POI_TITILE) );
        ((TextView)findViewById(R.id.poi_description)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_DESCR));
        ((TextView)findViewById(R.id.poi_latitude)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_LATITUDE));
        ((TextView)findViewById(R.id.poi_longitude)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_LONGITUDE));
		*/

        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {

        end_latitude = Double.parseDouble(getIntent().getExtras().getString(EXTRAS_KEY_POI_LATITUDE));
        end_longitude = Double.parseDouble(getIntent().getExtras().getString(EXTRAS_KEY_POI_LONGITUDE));
        end_title = getIntent().getExtras().getString(EXTRAS_KEY_POI_TITILE);
        end_description = getIntent().getExtras().getString(EXTRAS_KEY_POI_DESCR);

        mMap.addMarker(new MarkerOptions().position(new LatLng(end_latitude, end_longitude)).title(end_title).snippet(end_description)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.navioicon3)));

        mMap.getMaxZoomLevel();

        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

    } //-- END of the setUpMap() method

}
