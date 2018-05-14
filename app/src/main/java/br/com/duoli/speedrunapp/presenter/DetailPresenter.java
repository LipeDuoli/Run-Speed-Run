package br.com.duoli.speedrunapp.presenter;

import android.util.Log;

import br.com.duoli.speedrunapp.repository.LeaderboardRepository;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements DetailContract.Presenter {

    private final String TAG = DetailPresenter.class.getSimpleName();

    private LeaderboardRepository mLeaderboardRepository;
    private Scheduler mScheduler;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private DetailContract.View mView;
    private Leaderboard mLeaderboard;

    public DetailPresenter(LeaderboardRepository leaderboardRepository, Scheduler scheduler) {
        this.mLeaderboardRepository = leaderboardRepository;
        this.mScheduler = scheduler;
    }

    @Override
    public void setView(DetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadData(String gameId, String categoryId) {
        mView.displayLoading();
        if (mLeaderboard != null) {
            mView.displayLeaderboard(mLeaderboard);
        } else {
            loadLeaderboard(gameId, categoryId);
        }
    }

    @Override
    public void destroy() {
        mDisposable.clear();
    }

    private void loadLeaderboard(String gameId, String categoryId) {
        mDisposable.add(mLeaderboardRepository.getLeaderboard(gameId, categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<Leaderboard>() {
                    @Override
                    public void onSuccess(Leaderboard leaderboard) {
                        mLeaderboard = leaderboard;

                        if (mLeaderboard != null) {
                            mView.displayLeaderboard(mLeaderboard);
                        } else {
                            mView.displayNotFound();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.displayError();
                        Log.e(TAG, "loadLeaderboard: " + e.getMessage());
                    }
                }));
    }
}
