package br.com.duoli.speedrunapp.presenter;

import android.util.Log;

import java.util.List;

import br.com.duoli.speedrunapp.repository.GamesRepository;
import br.com.duoli.sr4j.models.common.PageableList;
import br.com.duoli.sr4j.models.common.Pagination;
import br.com.duoli.sr4j.models.games.Game;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GamesPresenter implements GamesContract.Presenter {

    private final String TAG = GamesPresenter.class.getSimpleName();
    private static final int FIRST_PAGE = 0;

    private GamesRepository mGamesRepository;
    private Scheduler mScheduler;
    private CompositeDisposable disposable = new CompositeDisposable();
    private GamesContract.View mView;
    private List<Game> mGames;
    private Pagination mLastPage;

    public GamesPresenter(GamesRepository gamesRepository, Scheduler scheduler) {
        mGamesRepository = gamesRepository;
        mScheduler = scheduler;
    }

    @Override
    public void setView(GamesContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadData() {
        mView.displayLoading();
        if (mGames != null){
            mView.displayGames(mGames);
        } else {
            loadGames(FIRST_PAGE);
        }
    }

    @Override
    public void reloadData(boolean displayLoading) {
        if (displayLoading){
            mView.displayLoading();
        }
        loadGames(FIRST_PAGE);
    }

    @Override
    public void destroy() {
        disposable.clear();
    }

    @Override
    public void loadMore() {
        loadGames(mLastPage.getOffset() + mLastPage.getSize());
    }

    private void loadGames(final int page) {
        disposable.add(mGamesRepository.getGames(page)
                .subscribeOn(Schedulers.io())
                .observeOn(mScheduler)
                .subscribeWith(new DisposableSingleObserver<PageableList<Game>>() {
                    @Override
                    public void onSuccess(PageableList<Game> gamePageableList) {
                        if (page == FIRST_PAGE){
                            mGames = gamePageableList.getData();
                        } else {
                            mGames.addAll(gamePageableList.getData());
                        }

                        mLastPage = gamePageableList.getPagination();
                        if (mGames.size() > 0){
                            mView.displayGames(mGames);
                        } else {
                            mView.displayNotFound();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (page == FIRST_PAGE){
                            mView.displayError();
                        }
                        Log.e(TAG, "loadGames: " + e.getMessage());
                    }
                }));
    }
}
