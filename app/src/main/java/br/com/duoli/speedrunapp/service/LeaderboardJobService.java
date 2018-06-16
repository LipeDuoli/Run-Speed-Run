package br.com.duoli.speedrunapp.service;

import android.content.Context;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.util.List;

import br.com.duoli.speedrunapp.model.FavoriteGame;
import br.com.duoli.speedrunapp.repository.FavoriteRepository;
import br.com.duoli.speedrunapp.repository.FavoriteRepositoryImpl;
import br.com.duoli.speedrunapp.repository.LeaderboardRepository;
import br.com.duoli.speedrunapp.repository.LeaderboardRepositoryImpl;
import br.com.duoli.speedrunapp.tools.NotificationUtils;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import br.com.duoli.sr4j.models.runs.Run;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LeaderboardJobService extends JobService {

    private static final String TAG = LeaderboardJobService.class.getSimpleName();
    private CompositeDisposable disposable = new CompositeDisposable();
    private Context context;
    private FavoriteRepository favoriteRepository;
    private LeaderboardRepository leaderboardRepository;

    @Override
    public boolean onStartJob(final JobParameters job) {
        context = LeaderboardJobService.this;
        favoriteRepository = new FavoriteRepositoryImpl(context);
        leaderboardRepository = new LeaderboardRepositoryImpl();

        disposable.add(favoriteRepository.loadFavoriteGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<FavoriteGame>>() {
                    @Override
                    public void onSuccess(List<FavoriteGame> gameList) {
                        for (FavoriteGame game : gameList) {
                            checkFirstPlace(game);
                        }
                        jobFinished(job, false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onStartJob: ", e);
                    }
                }));

        return true;
    }

    private void checkFirstPlace(final FavoriteGame game) {
        disposable.add(leaderboardRepository
                .getLeaderboard(game.getGameId(), game.getCategoryId(), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Leaderboard>() {
                    @Override
                    public void onSuccess(Leaderboard leaderboard) {
                        if (leaderboard.getRuns().size() > 0) {
                            Run run = leaderboard.getRuns().get(0).getRun();
                            if (!run.getId().equals(game.getFirstPlaceId())) {
                                NotificationUtils.showHasNewLeaderboard(
                                        context,
                                        game,
                                        run.getPlayers().get(0).getName(),
                                        run.getTimes().getPrimary());

                                favoriteRepository.updateFirstPlace(game.getId(), run.getId());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "checkFirstPlace: ", e);
                    }
                }));
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        disposable.clear();
        return true;
    }
}
