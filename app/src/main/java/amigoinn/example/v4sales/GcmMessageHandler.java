package amigoinn.example.v4sales;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.v4sales.R;
import com.google.android.gms.gcm.GcmListenerService;
import amigoinn.walkietalkie.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maulik Patel on 10/15/2015.
 */
public class GcmMessageHandler extends GcmListenerService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;

    DatabaseHandler handler;
    public static int count=0;
    @Override
    public void onMessageReceived(String from, Bundle data)
    {
        String message = data.getString("message");
        String messageSplitted=message.substring(0, 5);
        String messagefinal=message.substring(6);
        handler=new DatabaseHandler(getApplicationContext());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String date1=dateFormat.format(date);
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
        String monthname=dateFormat1.format(date);
        handler.addMessage(date1, message, monthname);
        if(messageSplitted.equalsIgnoreCase("alert"))
        {

            SharedPreferences preferences=getSharedPreferences("notification",Context.MODE_PRIVATE);
            SharedPreferences.Editor edit=preferences.edit();
            edit.clear();
            edit.putString("message", messagefinal);
            edit.commit();
            createNotificationAttendence(from, messagefinal);
        }
        else
        {
            createNotification(from, message);
        }
    }

    // Creates notification based on title and body received
    private void createNotification(String title, String body) {
        Context context = getBaseContext();
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, body, when);
      //  Notification notification = new Notification().Builder(getApplicationContext());
        String titleMy = context.getString(R.string.app_name);

        Intent notificationIntent = new Intent(context, LeftMenusActivity.class);
        // set intent so it does not start a new activity

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        count++;
        notification.number=count;

        notification.setLatestEventInfo(context, titleMy, body, intent);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");

        // Vibrate if vibrate is enabled
	        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("P.R.I.S.M")
//                .setContentText(body);
//        NotificationManager mNotificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());

    }
    private void createNotificationAttendence(String title, String body) {
        Context context = getBaseContext();
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, body, when);
        //  Notification notification = new Notification().Builder(getApplicationContext());
        String titleMy = context.getString(R.string.app_name);

        Intent notificationIntent = new Intent(context, AttendenceActivity.class);
        // set intent so it does not start a new activity

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        count++;
        notification.number=count;

        notification.setLatestEventInfo(context, titleMy, body, intent);

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("P.R.I.S.M")
//                .setContentText(body);
//        NotificationManager mNotificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());

    }


}
