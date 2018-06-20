package br.com.duoli.speedrunapp.ui.main.games;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import br.com.duoli.speedrunapp.presenter.GamesContract;
import br.com.duoli.speedrunapp.presenter.GamesPresenter;
import br.com.duoli.speedrunapp.repository.GamesRepositoryImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class GameLoader extends Loader<GamesContract.Presenter> {

    private GamesContract.Presenter mPresenter;

    GameLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mPresenter != null) {
            deliverResult(mPresenter);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        deliverResult(new GamesPresenter(new GamesRepositoryImpl(), AndroidSchedulers.mainThread()));
    }

    @Override
    public void deliverResult(@Nullable GamesContract.Presenter data) {
        if (mPresenter == null) {
            mPresenter = data;
        }
        super.deliverResult(data);
    }
}
