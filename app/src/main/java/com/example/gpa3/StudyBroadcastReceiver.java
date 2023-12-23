package com.example.gpa3;// MyReceiver.java

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StudyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("com.example.gpa3.STUDY_BROADCAST")) {
            Toast.makeText(context, "Received  Broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}

