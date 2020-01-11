package com.progrema.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        String log = sb.toString();
        Log.d("DEBUG", log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
    }

}
