package com.example.android.notificationpractise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.android.notificationpractise.App.CHANNEL_1_ID;
import static com.example.android.notificationpractise.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat mNotificationManager;
    private EditText mEditTextTitle;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding views
        mNotificationManager = NotificationManagerCompat.from(this);
        mEditTextTitle = findViewById(R.id.edit_text_title);
        mEditTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View view) {
        String title = mEditTextTitle.getText().toString();
        String text = mEditTextMessage.getText().toString();

        //to start an activity when the notification is clicked
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        //To show an action bar which you can use to carry out an action in your app when clicked using broadcast receivers
        //create the class NotificationReceiver
        //don't forget to add the broadcast receiver to manifest
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", text);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
//                //to show text in one line
                .setContentText(text)
                //to show text in more than one line
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                //for the pending intent
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                //adding the broadcast receivers, here and manifest
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();

        mNotificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View view) {
        String title = mEditTextTitle.getText().toString();
        String text = mEditTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        mNotificationManager.notify(2, notification);
    }
}
