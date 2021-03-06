package br.com.duoli.speedrunapp.presenter;

import android.util.Log;

import br.com.duoli.speedrunapp.repository.FavoriteRepository;
import br.com.duoli.speedrunapp.repository.LeaderboardRepository;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LeaderboardPresenter implements LeaderboardContract.Presenter {

    private final String TAG = LeaderboardPresenter.class.getSimpleName();

    private LeaderboardRepository mLeaderboardRepository;
    private FavoriteRepository mFavoriteRepository;
    private Scheduler mScheduler;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private LeaderboardContract.View mView;
    private Leaderboard mLeaderboard;
    private String mGameId = "";
    private String mCategoryId = "";

    public LeaderboardPresenter(LeaderboardRepository leaderboardRepository,
                                FavoriteRepository favoriteRepository,
                                Scheduler scheduler) {
        this.mLeaderboardRepository = leaderboardRepository;
        this.mFavoriteRepository = favoriteRepository;
        this.mScheduler = scheduler;
    }

    @Override
    public void setView(LeaderboardContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadData(String gameId, String categoryId) {
        mGameId = gameId;
        mCategoryId = categoryId;

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
        mDisposable.add(mLeaderboardRepository.getLeaderboard(gameId, categoryId, 30)
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<Leaderboard>() {
                    @Override
                    public void onSuccess(Leaderboard leaderboard) {
                        mLeaderboard = leaderboard;

                        if (mLeaderboard != null && mLeaderboard.getRuns().size() > 0) {
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

    @Override
    public void reloadData(boolean displayLoading) {
        if (displayLoading) {
            mView.displayLoading();
        }
        loadLeaderboard(mGameId, mCategoryId);
    }

    @Override
    public void favoriteLeaderboard() {
        boolean inserted = mFavoriteRepository.favoriteLeaderboard(mLeaderboard);
        if (inserted) {
            mView.showFavoriteAdded();
        } else {
            mView.showErroAddFavorite();
        }
    }

    @Override
    public void removeFavoriteLeaderboard() {
        int count = mFavoriteRepository.removeFavoriteLeaderboard(mGameId, mCategoryId);
        if (count > 0) {
            mView.showFavoriteRemoved();
        } else {
            mView.showErroRemoveFavorite();
        }
    }

    @Override
    public boolean isLeaderboardFavorited() {
        return mFavoriteRepository.isfavorited(mGameId, mCategoryId);
    }
}
