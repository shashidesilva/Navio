package lk.navio.cdap.navio.welcome.ARCameraView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import lk.navio.cdap.navio.welcome.R;

public class NativePOICamera extends CameraActivity {

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.injectData();
    }






}
