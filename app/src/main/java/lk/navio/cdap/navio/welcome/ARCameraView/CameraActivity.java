package lk.navio.cdap.navio.welcome.ARCameraView;

import android.content.Intent;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.net.Uri;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import lk.navio.cdap.navio.welcome.R;
import lk.navio.cdap.navio.welcome.Welcome;


public class CameraActivity extends ARCameraActivity {

    private long lastCalibrationToastShownTimeMillis = System.currentTimeMillis();

    @Override
    protected StartupConfiguration.CameraPosition getCameraPosition() {
       return StartupConfiguration.CameraPosition.DEFAULT;
    }

    @Override
    protected boolean hasGeo() {
        //return getIntent().getExtras().getBoolean(
                //
        return true;
    }

    @Override
    protected boolean hasIR() {
        //return getIntent().getExtras().getBoolean(
                //Welcome.EXTRAS_KEY_ACTIVITY_IR);
        return true;
    }

    @Override
    public String getARchitectWorldPath() {
        return "ARWorld.html";
    }

    @Override
    public ArchitectView.ArchitectUrlListener getUrlListener() {
        return new ArchitectView.ArchitectUrlListener() {
            @Override
            public boolean urlWasInvoked(String uriString) {
                Uri invokedUri = Uri.parse(uriString);

                if ("markerselected".equalsIgnoreCase(invokedUri.getHost())) {
                    final Intent poiDetailIntent = new Intent(CameraActivity.this, JsToNativeData.class);
                    poiDetailIntent.putExtra(JsToNativeData.EXTRAS_KEY_POI_ID, String.valueOf(invokedUri.getQueryParameter("id")) );
                    poiDetailIntent.putExtra(JsToNativeData.EXTRAS_KEY_POI_TITILE, String.valueOf(invokedUri.getQueryParameter("title")) );
                    poiDetailIntent.putExtra(JsToNativeData.EXTRAS_KEY_POI_DESCR, String.valueOf(invokedUri.getQueryParameter("description")) );
                    poiDetailIntent.putExtra(JsToNativeData.EXTRAS_KEY_POI_LATITUDE, String.valueOf(invokedUri.getQueryParameter("lat")) );
                    poiDetailIntent.putExtra(JsToNativeData.EXTRAS_KEY_POI_LONGITUDE, String.valueOf(invokedUri.getQueryParameter("lon")) );
                    CameraActivity.this.startActivity(poiDetailIntent);
                    return true;
                }
                return true;
            }
        };

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_arcamera;
    }

    @Override
    public String getWikitudeSDKLicenseKey() {
        return "uPuJ6WGapvnis4F0vzcuKREuED2USrwYSz1zw4HkboOZWd1xBwVk47eBVZ8r1CUAVCl+7sB1dfEi9Goms6mIOQqqSPIQI2oVwDqxbI358bf5Lgn/ABiYvZ8wp62PquYeJVyvSrL/GMjBNn3ynss7zwy6VjcbYIhRzwdoVgiEKWVTYWx0ZWRfX2dEDoXe49j6GM8lJgiJzejQ3CpS5J8HcxhQjeQfBAxmrWnQRPeMgkxUV0xZjiH/69mzavd2ViilubDtwvtcRNRiWEelNwrkSHADiS73HeW5jBMEzUq1teHM04AVKygr5n46qlgkOrOGvQvT0fRAXzeI276CZtF0UDnFRs48aBYNeHcs4CCkcaMF63E77kTr0TZk8j6o2MdWdaLotiOYkXH7IE3mPVbKdXcm8Uypzol43gV9zvHUuwCv60ZCxzhLaS3vBIWPnREYUt21bMVXfHG/9wz1rIPZxrnx6y+cwRVoowqE2gOC8ePuXS7098fQDDKGglvyeN59jVWjKnqOIfO7znY9whcr76a1D6vOrRtS3aQGRfnTdTRqbGhuOiqjiQPli9ZSiWMHREFyaDn8dI0A1cPFEPFKtmhli3SHaMhQl6B4bChxey/1KABLHLagK6Nj7bTMk4F+45K2uk8GJuLvfLHhnNMSMRVbnAS/5wdSXHLsQa8lwck=";
    }

    @Override
    public int getArchitectViewId() {
        return R.id.architectView;
    }

    @Override
    public ILocationProvider getLocationProvider(final LocationListener locationListener) {
        return new LocationProvider(this, locationListener);
    }

    @Override
    public ArchitectView.SensorAccuracyChangeListener getSensorAccuracyListener() {
        return new ArchitectView.SensorAccuracyChangeListener() {
            @Override
            public void onCompassAccuracyChanged( int accuracy ) {
				/* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, HIGH = 3 */
                if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && CameraActivity.this != null && !CameraActivity.this.isFinishing() && System.currentTimeMillis() - CameraActivity.this.lastCalibrationToastShownTimeMillis > 5 * 1000) {
                    Toast.makeText(CameraActivity.this, "compass accuracy is low", Toast.LENGTH_LONG).show();
                    CameraActivity.this.lastCalibrationToastShownTimeMillis = System.currentTimeMillis();
                }
            }
        };
    }

    @Override
    public float getInitialCullingDistanceMeters() {
        // you need to adjust this in case your POIs are more than 50km away from user here while loading or in JS code (compare 'AR.context.scene.cullingDistance')
        return ArchitectViewHolderInterface.CULLING_DISTANCE_DEFAULT_METERS;
    }
}
