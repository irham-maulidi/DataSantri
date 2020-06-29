package com.example.data_santri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class Alarm extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 134;
    //set interval notifikasi 1 menit
    private int interval_seconds = 60;
    private int NOTIFICATION_ID = 1;
    //shared refrence
    private int mColor;
    private final String COLOR_KEY = "color";

    private Button BStart, BStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent alarmIntent = new Intent(Alarm.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Alarm.this, ALARM_REQUEST_CODE, alarmIntent, 0);

        BStart = findViewById(R.id.bStart);
        BStop = findViewById(R.id.bStop);

        //shared reference
        mColor = ContextCompat.getColor(this, R.color.default_background);

        // Restore the saved instance state.
        if (savedInstanceState != null) {

            mColor = savedInstanceState.getInt(COLOR_KEY);
            BStart.setBackgroundColor(mColor);
            BStop.setBackgroundColor(mColor);
        }
    }
    public void startAlarmManager(View v) {
        //set waktu sekarang berdasarkan interval
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, interval_seconds);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //set alarm manager dengan memasukkan waktu yang telah dikonversi menjadi milliseconds
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "AlarmManager Start.", Toast.LENGTH_SHORT).show();
    }
    //Stop/Cancel alarm manager
    public void stopAlarmManager(View v) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        //close existing/current notifications
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
        //jika app ini mempunyai banyak notifikasi bisa di cancelAll()
        //notificationManager.cancelAll();
        Toast.makeText(this, "AlarmManager Stopped by User.", Toast.LENGTH_SHORT).show();
    }
    //shared reference
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        BStart.setBackgroundColor(mColor);
        BStop.setBackgroundColor(mColor);
        mColor = color;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COLOR_KEY, mColor);
    }

}