package br.com.duoli.speedrunapp.tools;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

import br.com.duoli.speedrunapp.service.LeaderboardJobService;

public class LeaderboardNotificationUtils {

    private LeaderboardNotificationUtils() {
    }

    private static final int INTERVAL_MINUTES = 60;
    private static final int INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(INTERVAL_MINUTES);
    private static final int FLEXTIME_MINUTES = 15;
    private static final int FLEXTIME_SECONDS = (int) TimeUnit.MINUTES.toSeconds(FLEXTIME_MINUTES);

    private static final String JOB_TAG = "leaderboard_taken_notification";
    private static boolean sInitialized;

    synchronized public static void scheduleLeaderboardCheck(@NonNull final Context context){
        if (sInitialized) return;

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

        Job leaderboardCheckJob = dispatcher.newJobBuilder()
                .setService(LeaderboardJobService.class)
                .setTag(JOB_TAG)
                .setConstraints(Constraint.ON_UNMETERED_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        INTERVAL_SECONDS,
                        INTERVAL_SECONDS + FLEXTIME_SECONDS))
                .setRecurring(true)
                .build();

        dispatcher.mustSchedule(leaderboardCheckJob);
        sInitialized = true;
    }
}
