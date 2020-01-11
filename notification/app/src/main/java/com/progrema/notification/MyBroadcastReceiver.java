package com.progrema.notification;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        String log = sb.toString();
        Log.d("DEBUG", log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();

        if (intent.getAction().equals("ACTION_REPLY")) {
            Log.d("DEBUG", getMessageText(intent).toString());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification repliedNotification = new Notification.Builder(context, "default")
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentText("Sent")
                        .build();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(1, repliedNotification);
            }

        }
    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence("key_text_reply");
        }
        return null;
    }
}
