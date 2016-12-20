package cat.udl.menufinder.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;

import cat.udl.menufinder.R;
import cat.udl.menufinder.application.MasterActivity;
import cat.udl.menufinder.enums.UserType;
import cat.udl.menufinder.ws.WebServiceSync;

public class SplashActivity extends MasterActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeOrientationIfIsPhone();
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        final ImageView logo = (ImageView) findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getMasterApplication().isLogged()) {
                    UserType userType = getMasterApplication().getUserType();
                    switch (userType) {
                        case CLIENT:
                        case RESTAURANT:
                        case GUEST:
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();
                            break;
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    if (Build.VERSION.SDK_INT >= 21) {
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this, logo, getString(R.string.shared_logo));
                        startActivity(intent, activityOptionsCompat.toBundle());
                    } else {
                        startActivity(intent);
                    }
                    finish();
                }
            }
        }, 1000);

        WebServiceSync.getInstance().syncAllData();
    }
}
