package br.com.duoli.speedrunapp.presenter;

import android.util.Log;

import br.com.duoli.speedrunapp.repository.GamesRepository;
import br.com.duoli.sr4j.models.games.Game;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements DetailContract.Presenter {

    private final String TAG = DetailPresenter.class.getSimpleName();

    private GamesRepository mGameRepository;
    private Scheduler mScheduler;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private DetailContract.View mView;
    private Game mGame;
    private String mGameId;

    public DetailPresenter(GamesRepository gameRepository, Scheduler scheduler) {
        this.mGameRepository = gameRepository;
        this.mScheduler = scheduler;
    }

    @Override
    public void setView(DetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadData(String gameId) {
        mGameId = gameId;
        mView.displayLoading();
        if (mGame != null) {
            mView.displayGameInfo(mGame);
        } else {
            loadGame(mGameId);
        }
    }

    @Override
    public void reloadData(boolean displayLoading) {
        if (displayLoading){
            mView.displayLoading();
        }
        loadGame(mGameId);
    }

    @Override
    public void destroy() {
        mDisposable.clear();
    }

    private void loadGame(String gameId) {
        mDisposable.add(mGameRepository.getGame(gameId)
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<Game>() {
                    @Override
                    public void onSuccess(Game game) {
                        mGame = game;

                        if (mGame != null) {
                            mView.displayGameInfo(mGame);
                        } else {
                            mView.displayNotFound();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.displayError();
                        Log.e(TAG, "loadGame: " + e.getMessage());
                    }
                }));
    }

}
