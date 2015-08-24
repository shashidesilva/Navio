package lk.navio.cdap.navio.welcome.ARCameraView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import lk.navio.cdap.navio.welcome.R;

public class JsToNativeData extends ActionBarActivity {

    public static final String EXTRAS_KEY_POI_ID = "id";
    public static final String EXTRAS_KEY_POI_TITILE = "title";
    public static final String EXTRAS_KEY_POI_DESCR = "description";
    public static final String EXTRAS_KEY_POI_LATITUDE = "latitude";
    public static final String EXTRAS_KEY_POI_LONGITUDE = "longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_to_native_data);

        ((TextView)findViewById(R.id.poi_id)).setText(  getIntent().getExtras().getString(EXTRAS_KEY_POI_ID) );
        ((TextView)findViewById(R.id.poi_title)).setText( getIntent().getExtras().getString(EXTRAS_KEY_POI_TITILE) );
        ((TextView)findViewById(R.id.poi_description)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_DESCR));
        ((TextView)findViewById(R.id.poi_latitude)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_LATITUDE));
        ((TextView)findViewById(R.id.poi_longitude)).setText(getIntent().getExtras().getString(EXTRAS_KEY_POI_LONGITUDE));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_js_to_native_data, menu);
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
