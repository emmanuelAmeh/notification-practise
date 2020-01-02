package com.example.android.notificationpractise;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name1 = getString(R.string.channel_name_1);
            CharSequence name2 = getString(R.string.channel_name_2);
            String description1 = getString(R.string.channel_description);
            String description2 = getString(R.string.channel_description_2);
            int importance1 = NotificationManager.IMPORTANCE_HIGH;
            int importance2 = NotificationManager.IMPORTANCE_LOW;

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, name1, importance1);
            channel1.setDescription(description1);

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID, name2, importance2);
            channel2.setDescription(description2);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager1 = getSystemService(NotificationManager.class);
            notificationManager1.createNotificationChannel(channel1);

            NotificationManager notificationManager2 = getSystemService(NotificationManager.class);
            notificationManager2.createNotificationChannel(channel2);
        }
    }
}
