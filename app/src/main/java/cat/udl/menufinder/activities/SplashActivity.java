package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;

public class SplashActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        final ImageView logo = (ImageView) findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this, logo, getString(R.string.shared_logo));
                startActivity(new Intent(SplashActivity.this, LoginActivity.class), activityOptionsCompat.toBundle());
                finish();
            }
        }, 1000);
    }
}
