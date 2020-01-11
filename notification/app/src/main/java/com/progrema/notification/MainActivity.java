package com.progrema.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action(view);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void action(View view) {

        //
        // Need to create notification channel for android version above API Level 26
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "My Notification Channel Description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //
        // Intent to go to MainActivity if user press the notification
        //
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //
        // Add snooze action to notification. This will be handled by MyBroadcastReceiver class
        //
        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction("ACTION_SNOOZE");
        snoozeIntent.putExtra("EXTRA_NOTIFICATION_ID_SNOOZE", 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

        //
        // Add cancel action to notification. This will be handled by MyBroadcastReceiver class
        //
        Intent cancelIntent = new Intent(this, MyBroadcastReceiver.class);
        cancelIntent.setAction("ACTION_CANCEL");
        cancelIntent.putExtra("EXTRA_NOTIFICATION_ID_CANCEL", 0);
        PendingIntent cancelPendingIntent =
                PendingIntent.getBroadcast(this, 0, cancelIntent, 0);

        //
        // Build the notification with all its options
        //
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("My Notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_palette_black_32dp, "SNOOZE", snoozePendingIntent)
                .addAction(R.drawable.ic_palette_black_32dp, "CANCEL", cancelPendingIntent)
                .setAutoCancel(true);

        //
        // Show the notification to user interface
        //
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());

    }
}
