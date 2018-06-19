package br.com.duoli.speedrunapp.tools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.model.FavoriteGame;
import br.com.duoli.speedrunapp.ui.detail.DetailActivity;
import br.com.duoli.speedrunapp.ui.main.MainActivity;

public class NotificationUtils {

    private static final String TAG = NotificationUtils.class.getSimpleName();
    private static final String LEADERBOARD_NOTIFICATION_CHANNEL_ID = "leaderboard_notification_channel";

    private NotificationUtils() {
    }

    public static void showHasNewLeaderboard(final Context context,
                                             final FavoriteGame game,
                                             final String primaryTime) {
        final NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    LEADERBOARD_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        //get image to add to notification
        Picasso.get().load(game.getFirstPlaceAssetPath()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Notification notification = buildNotification(context,
                        game,
                        bitmap,
                        primaryTime);

                notificationManager.notify(game.getId(), notification);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.e(TAG, "onBitmapFailed: ", e);

                Notification notification = buildNotification(context,
                        game,
                        null,
                        primaryTime);

                notificationManager.notify(game.getId(), notification);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    private static PendingIntent contentIntent(Context context, String gameId, int id) {
        Intent startActivityIntent = DetailActivity.newInstance(context, gameId);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(startActivityIntent);

        return stackBuilder.getPendingIntent(
                id,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Notification buildNotification(Context context,
                                                  FavoriteGame game,
                                                  Bitmap gameImage,
                                                  String time) {
        String title = context.getString(R.string.notification_title, game.getGameName());
        String bodyText = context.getString(R.string.notification_body,
                game.getCategoryName(),
                StringUtils.parseRunTime(time));

        NotificationCompat.Builder notificationBuilder = new NotificationCompat
                .Builder(context, LEADERBOARD_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_trophy)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle(title)
                        .bigText(bodyText))
                .setContentTitle(title)
                .setContentText(bodyText)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context, game.getGameId(), game.getId()))
                .setAutoCancel(true);

        if (gameImage != null) {
            notificationBuilder.setLargeIcon(gameImage);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        return notificationBuilder.build();
    }
}
