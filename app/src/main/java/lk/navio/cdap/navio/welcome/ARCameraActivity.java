package lk.navio.cdap.navio.welcome;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import java.io.IOException;


public class ARCameraActivity extends ActionBarActivity {

    ArchitectView architectView;
    protected static final String SDK_KEY = "uPuJ6WGapvnis4F0vzcuKREuED2USrwYSz1zw4HkboOZWd1xBwVk47eBVZ8r1CUAVCl+7sB1dfEi9Goms6mIOQqqSPIQI2oVwDqxbI358bf5Lgn/ABiYvZ8wp62PquYeJVyvSrL/GMjBNn3ynss7zwy6VjcbYIhRzwdoVgiEKWVTYWx0ZWRfX2dEDoXe49j6GM8lJgiJzejQ3CpS5J8HcxhQjeQfBAxmrWnQRPeMgkxUV0xZjiH/69mzavd2ViilubDtwvtcRNRiWEelNwrkSHADiS73HeW5jBMEzUq1teHM04AVKygr5n46qlgkOrOGvQvT0fRAXzeI276CZtF0UDnFRs48aBYNeHcs4CCkcaMF63E77kTr0TZk8j6o2MdWdaLotiOYkXH7IE3mPVbKdXcm8Uypzol43gV9zvHUuwCv60ZCxzhLaS3vBIWPnREYUt21bMVXfHG/9wz1rIPZxrnx6y+cwRVoowqE2gOC8ePuXS7098fQDDKGglvyeN59jVWjKnqOIfO7znY9whcr76a1D6vOrRtS3aQGRfnTdTRqbGhuOiqjiQPli9ZSiWMHREFyaDn8dI0A1cPFEPFKtmhli3SHaMhQl6B4bChxey/1KABLHLagK6Nj7bTMk4F+45K2uk8GJuLvfLHhnNMSMRVbnAS/5wdSXHLsQa8lwck=";

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

    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (this.architectView != null) {

            // call mandatory live-cycle method of architectView
            this.architectView.onPostCreate();

            try {
                // load content via url in architectView, ensure '<script src="architect://architect.js"></script>' is part of this HTML file, have a look at wikitude.com's developer section for API references
                this.architectView.load("ARWorld.html");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // call mandatory live-cycle method of architectView
        if (this.architectView != null) {
            this.architectView.onResume();

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        // call mandatory live-cycle method of architectView
        if (this.architectView != null) {
            this.architectView.onPause();
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
