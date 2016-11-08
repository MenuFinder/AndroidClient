package cat.udl.menufinder;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SplashActivity.this, "Go to next activity", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }
}
