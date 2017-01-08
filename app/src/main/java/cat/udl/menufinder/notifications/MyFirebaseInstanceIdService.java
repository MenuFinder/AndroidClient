package cat.udl.menufinder.notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import cat.udl.menufinder.application.MasterApplication;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = MyFirebaseInstanceIdService.class.getName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token : " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        ((MasterApplication)getApplication()).setToken(refreshedToken);
    }
}
