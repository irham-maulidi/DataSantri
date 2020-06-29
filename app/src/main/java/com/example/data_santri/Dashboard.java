package com.example.data_santri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    //shared refrence
    private int mColor;
    private final String COLOR_KEY = "color";
    private Button Addsantri, Notification, Alarm, Send;

    Button bt_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Addsantri = findViewById(R.id.imageButton6);
        Notification = findViewById(R.id.button4);
        Alarm = findViewById(R.id.button5);
        Send = findViewById(R.id.button);
        //shared reference
        mColor = ContextCompat.getColor(this, R.color.default_background);

        bt_notification = findViewById(R.id.button4);
        bt_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message= "Welcome to my application, Data_Santri " +
                        "In this application for the final task matkul advanced mobile devices";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        Dashboard.this
                )
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle("New Notification")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent= new Intent(Dashboard.this,NotifActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);
                PendingIntent pendingIntent= PendingIntent.getActivity(Dashboard.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager= (NotificationManager)getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
                notificationManager.notify(0,builder.build());
            }
        });
    }
    public void add(View view) {
        Intent intent = new Intent(Dashboard.this, MainActivity.class);
        startActivity(intent);
    }
    public void kirim(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"irham16101999@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "this is email");
        intent.putExtra(Intent.EXTRA_TEXT, "what do you thing? ");

        try {
            startActivity(Intent.createChooser(intent, "Do you want send Email?"));
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    public void Alarm(View view) {
        Intent intent = new Intent(Dashboard.this, Alarm.class);
        startActivity(intent);
    }

    //shared reference
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        Addsantri.setBackgroundColor(mColor);
        Notification.setBackgroundColor(mColor);
        Alarm.setBackgroundColor(mColor);
        Send.setBackgroundColor(mColor);
        mColor = color;
    }

}
