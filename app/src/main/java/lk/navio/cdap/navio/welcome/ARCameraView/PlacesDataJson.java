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
        poiInformation.put(ATTR_NAME, "Kumara");
        poiInformation.put(ATTR_DESCRIPTION, "Kade");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.856678");
        poiInformation.put(ATTR_LONGITUDE, "80.039218");
        final float UNKNOWN_ALTITUDE = -32768f;  // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "2");
        poiInformation.put(ATTR_NAME, "Liberty");
        poiInformation.put(ATTR_DESCRIPTION, "Plaza");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.911648");
        poiInformation.put(ATTR_LONGITUDE, "79.851297");
         // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "3");
        poiInformation.put(ATTR_NAME, "Dinemore");
        poiInformation.put(ATTR_DESCRIPTION, "Restaurant");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.912588");
        poiInformation.put(ATTR_LONGITUDE, "79.852864");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));



        poiInformation.put(ATTR_ID, "4");
        poiInformation.put(ATTR_NAME, "HNB");
        poiInformation.put(ATTR_DESCRIPTION, "Bank");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.911800");
        poiInformation.put(ATTR_LONGITUDE, "79.852177");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "5");
        poiInformation.put(ATTR_NAME, "Liberty");
        poiInformation.put(ATTR_DESCRIPTION, "Cinama");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.912367");
        poiInformation.put(ATTR_LONGITUDE, "79.851026");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "6");
        poiInformation.put(ATTR_NAME, "Temple");
        poiInformation.put(ATTR_DESCRIPTION, "Trees");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.914811");
        poiInformation.put(ATTR_LONGITUDE, "79.849631");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "7");
        poiInformation.put(ATTR_NAME, "US");
        poiInformation.put(ATTR_DESCRIPTION, "Embassy");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.912945");
        poiInformation.put(ATTR_LONGITUDE, "79.848612");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "8");
        poiInformation.put(ATTR_NAME, "Methodist");
        poiInformation.put(ATTR_DESCRIPTION, "College");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.911722");
        poiInformation.put(ATTR_LONGITUDE, "79.848492");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "9");
        poiInformation.put(ATTR_NAME, "NSB");
        poiInformation.put(ATTR_DESCRIPTION, "Bank");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.910934");
        poiInformation.put(ATTR_LONGITUDE, "79.850267");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "10");
        poiInformation.put(ATTR_NAME, "LB");
        poiInformation.put(ATTR_DESCRIPTION, "Finance");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.911658");
        poiInformation.put(ATTR_LONGITUDE, "79.850452");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "12");
        poiInformation.put(ATTR_NAME, "SLIIT");
        poiInformation.put(ATTR_DESCRIPTION, "ENG");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.916040");
        poiInformation.put(ATTR_LONGITUDE, "79.973183");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "13");
        poiInformation.put(ATTR_NAME, "SLIIT");
        poiInformation.put(ATTR_DESCRIPTION, "IT");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.914703");
        poiInformation.put(ATTR_LONGITUDE, "79.973135");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "14");
        poiInformation.put(ATTR_NAME, "SLIIT");
        poiInformation.put(ATTR_DESCRIPTION, "BM");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.914298");
        poiInformation.put(ATTR_LONGITUDE, "79.973400");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "14");
        poiInformation.put(ATTR_NAME, "SLIIT");
        poiInformation.put(ATTR_DESCRIPTION, "CAHM");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.915177");
        poiInformation.put(ATTR_LONGITUDE, "79.974589");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "15");
        poiInformation.put(ATTR_NAME, "Dilanka");
        poiInformation.put(ATTR_DESCRIPTION, "Food");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.915353");
        poiInformation.put(ATTR_LONGITUDE, "79.972022");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "16");
        poiInformation.put(ATTR_NAME, "Cargills");
        poiInformation.put(ATTR_DESCRIPTION, "Food City");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.914653");
        poiInformation.put(ATTR_LONGITUDE, "79.972061");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));

        poiInformation.put(ATTR_ID, "17");
        poiInformation.put(ATTR_NAME, "Luck");
        poiInformation.put(ATTR_DESCRIPTION, "Food");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.913884");
        poiInformation.put(ATTR_LONGITUDE, "79.972043");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        poiInformation.put(ATTR_ID, "18");
        poiInformation.put(ATTR_NAME, "Perera &");
        poiInformation.put(ATTR_DESCRIPTION, "Sons");
        //double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
        poiInformation.put(ATTR_LATITUDE, "6.914816");
        poiInformation.put(ATTR_LONGITUDE, "79.972128");
        // equals "AR.CONST.UNKNOWN_ALTITUDE" in JavaScript (compare AR.GeoLocation specification)
        // Use "AR.CONST.UNKNOWN_ALTITUDE" to tell ARchitect that altitude of places should be on user level. Be aware to handle altitude properly in locationManager in case you use valid POI altitude value (e.g. pass altitude only if GPS accuracy is <7m).
        poiInformation.put(ATTR_ALTITUDE, String.valueOf(UNKNOWN_ALTITUDE));
        poiData.put(new JSONObject(poiInformation));


        return poiData;

    }


}
