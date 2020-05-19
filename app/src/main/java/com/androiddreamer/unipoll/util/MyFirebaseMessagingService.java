package com.androiddreamer.unipoll.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.androiddreamer.unipoll.R;
import com.androiddreamer.unipoll.view.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String CHANNEL_ID = "CHANNEL_ID_0";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String notificationTitle = remoteMessage.getData().get("title");
        String notificationDescription = remoteMessage.getData().get("description");
        String notificationNewPollId = remoteMessage.getData().get("new_poll_id");

        createNotificationChannel();
        PendingIntent pendingIntent = createPendingIntent(notificationNewPollId);
        showNotification(notificationTitle, notificationDescription, pendingIntent);
    }

    private void showNotification(String notificationTitle, String notificationDescription, PendingIntent pendingIntent) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationDescription)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        }else{
            notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationDescription)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notifyId = (int) System.currentTimeMillis();
        notificationManager.notify(notifyId/* ID of notification */, notificationBuilder.build());
    }

    private PendingIntent createPendingIntent(String notificationNewPollId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("new_poll_id", notificationNewPollId);
        return PendingIntent.getActivity(this, (int) System.currentTimeMillis() /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Notification_channel_unipoll";
            String description = "Notifiation channel for receiving push notification from UniPoll app.";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel =  new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
