package cat.udl.menufinder;

import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showToast("Go to next activity");
            }
        }, 1000);
    }
}
