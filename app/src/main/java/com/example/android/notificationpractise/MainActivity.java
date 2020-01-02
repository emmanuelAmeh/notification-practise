package com.example.android.notificationpractise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;

import static com.example.android.notificationpractise.App.CHANNEL_1_ID;
import static com.example.android.notificationpractise.App.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat mNotificationManager;
    private EditText mEditTextTitle;
    private EditText mEditTextMessage;
    private MediaSessionCompat mMediaSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding views
        mNotificationManager = NotificationManagerCompat.from(this);
        mEditTextTitle = findViewById(R.id.edit_text_title);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        mMediaSession = new MediaSessionCompat(this, "tag");

    }

    public void sendOnChannel1(View view) {
        String title = mEditTextTitle.getText().toString();
        String text = mEditTextMessage.getText().toString();

        //to start an activity when the notification is clicked
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);


        //To add big icon
        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.three_men_solution);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                //to show text in one line
                .setContentText(text)
                //to show text in more than one line
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                //for the pending intent
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();

        mNotificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View view) {
        String album = mEditTextTitle.getText().toString();
        String song = mEditTextMessage.getText().toString();

        //To add big icon
        Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.three_men_solution);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(album)
                .setContentText(song)
                .setLargeIcon(artwork)
                .addAction(R.drawable.ic_dislike, "dislike", null)
                .addAction(R.drawable.ic_previous, "previous", null)
                .addAction(R.drawable.ic_play, "play", null)
                .addAction(R.drawable.ic_next, "next", null)
                .addAction(R.drawable.ic_like, "like", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3)
                        .setMediaSession(mMediaSession.getSessionToken()))
                .setSubText("Sub Text")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        mNotificationManager.notify(2, notification);
    }
}
