package cat.udl.menufinder.application;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cat.udl.menufinder.R;
import cat.udl.menufinder.database.DBManager;

public class MasterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public MasterApplication getMasterApplication() {
        return (MasterApplication) getApplication();
    }

    public DBManager getDbManager() {
        return getMasterApplication().getDbManager();
    }

    public void loadFragment(int resId, Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentById(resId) == null) {
            transaction.add(resId, fragment);
        } else {
            transaction.replace(resId, fragment);
        }
        transaction.commit();
    }

    public void showToast(int text_id) {
        getMasterApplication().showToast(text_id);
    }

    public void showToast(String text) {
        getMasterApplication().showToast(text);
    }

    public void changeOrientationIfIsPhone() {
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if (!isTablet) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
