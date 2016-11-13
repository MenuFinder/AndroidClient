package cat.udl.menufinder.application;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MasterActivity extends AppCompatActivity {

    public MasterApplication getMasterApplication() {
        return (MasterApplication) getApplication();
    }

    public void loadFragment(int resId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentById(resId) == null) {
            transaction.add(resId, fragment);
        } else {
            transaction.replace(resId, fragment);
        }
        transaction.commit();
    }

    public void showToast(int text_id) {
        Toast.makeText(MasterActivity.this, text_id, Toast.LENGTH_SHORT).show();
    }

    public void showToast(String text) {
        Toast.makeText(MasterActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
