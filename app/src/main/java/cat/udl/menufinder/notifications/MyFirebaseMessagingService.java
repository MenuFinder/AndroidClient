package cat.udl.menufinder.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import cat.udl.menufinder.R;
import cat.udl.menufinder.activities.SplashActivity;
import cat.udl.menufinder.utils.Constants;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        NotificationCompat.Builder mBuilder;
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        intent.putExtra(Constants.NOTIFICATION_RESTAURANT, remoteMessage.getData().get(Constants.NOTIFICATION_RESTAURANT));
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.menu_finder_logo)
                        .setContentTitle(notification.getTitle())
                        .setContentText(notification.getBody())
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }
}
