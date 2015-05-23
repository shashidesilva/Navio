package lk.navio.cdap.navio.welcome.ARCameraView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Tharaka on 5/23/2015.
 */
public class PlacesDataJson {


    public PlacesDataJson(){

    }

    public JSONArray getJsonPOIData(){

        final JSONArray poiData = new JSONArray();

        final String ATTR_ID = "id";
        final String ATTR_NAME = "name";
        final String ATTR_DESCRIPTION = "description";
        final String ATTR_LATITUDE = "latitude";
        final String ATTR_LONGITUDE = "longitude";
        final String ATTR_ALTITUDE = "altitude";

        final HashMap<String, String> poiInformation = new HashMap<String, String>();
        poiInformation.put(ATTR_ID, "1");
        poiInformation.put(ATTR_NAME, "HOME");
        poiInformation.put(ATTR_DESCRIPTION, "This is my place");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.855782");
        poiInformation.put(ATTR_LONGITUDE, "80.039050");
        final float UNKNOWN_ALTITUDE = -32768f;  // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        return poiData;

    }


}
