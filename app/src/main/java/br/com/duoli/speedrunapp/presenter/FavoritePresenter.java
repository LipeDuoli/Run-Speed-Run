package br.com.duoli.speedrunapp.presenter;

import android.util.Log;

import java.util.List;

import br.com.duoli.speedrunapp.model.FavoriteGame;
import br.com.duoli.speedrunapp.repository.FavoriteRepository;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private final String TAG = FavoritePresenter.class.getSimpleName();

    private FavoriteRepository mFavoriteRepository;
    private Scheduler mScheduler;
    private CompositeDisposable disposable = new CompositeDisposable();
    private FavoriteContract.View mView;
    private List<FavoriteGame> mGames;

    public FavoritePresenter(FavoriteRepository favoriteRepository, Scheduler scheduler) {
        mFavoriteRepository = favoriteRepository;
        mScheduler = scheduler;
    }

    @Override
    public void setView(FavoriteContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadData() {
        mView.displayLoading();
        if (mGames != null) {
            mView.displayGames(mGames);
        } else {
            loadGames();
        }
    }

    @Override
    public void reloadData(boolean displayLoading) {
        if (displayLoading) {
            mView.displayLoading();
        }
        loadGames();
    }

    @Override
    public void destroy() {
        disposable.clear();
    }

    private void loadGames() {
        disposable.add(mFavoriteRepository.loadFavoriteGames()
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<List<FavoriteGame>>() {
                    @Override
                    public void onSuccess(List<FavoriteGame> gameList) {
                        mGames = gameList;

                        if (mGames.size() > 0) {
                            mView.displayGames(mGames);
                        } else {
                            mView.displayNotFound();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "loadGames: " + e.getMessage());
                        mView.displayError();
                    }
                }));
    }
}
