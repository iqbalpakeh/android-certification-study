package com.progrema.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityB.this, MainActivityC.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                // Intent to go to MainActivityA if user press the notification
                //

                // Intent intent = new Intent(MainActivityB.this, MainActivityC.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // PendingIntent pendingIntent = PendingIntent.getActivity(MainActivityB.this, 0, intent, 0);

                Intent intent = new Intent(MainActivityB.this, MainActivityC.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivityB.this);
                stackBuilder.addNextIntentWithParentStack(intent);
                PendingIntent pendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                //
                // Build the notification with all its options
                //
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivityB.this, "default")
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle("My Notification")
                        .setContentText("Much longer text that cannot fit one line...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Much longer text that cannot fit one line..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                //
                // Show the notification to user interface
                //
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivityB.this);
                notificationManager.notify(0, builder.build());

            }
        });
    }

}
