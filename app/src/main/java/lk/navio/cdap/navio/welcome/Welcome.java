package lk.navio.cdap.navio.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import lk.navio.cdap.navio.welcome.ARCameraView.ARCameraActivity;


public class Welcome extends ActionBarActivity {

    ListView menuLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        menuLv = (ListView) findViewById(R.id.menuLv);

        String[] listValues = new String[]{"Augmented Reality Camera", "Nearby Location List",
                "Navio Translator"};

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < listValues.length; ++i) {
            list.add(listValues[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        menuLv.setAdapter(adapter);

        menuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                switch (position) {

                    case 0:
                        Intent arCam = new Intent(getApplicationContext(), ARCameraActivity.class);
                        startActivity(arCam);
                        break;

                    case 1:
                        Intent LocList = new Intent(getApplicationContext(), LocationListActivity.class);
                        startActivity(LocList);
                        break;

                    default:
                        Intent translator = new Intent(getApplicationContext(), TranslatorActivity.class);
                        startActivity(translator);

                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
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
