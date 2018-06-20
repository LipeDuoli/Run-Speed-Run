package br.com.duoli.speedrunapp.ui.main.favorites;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import br.com.duoli.speedrunapp.presenter.FavoriteContract;
import br.com.duoli.speedrunapp.presenter.FavoritePresenter;
import br.com.duoli.speedrunapp.repository.FavoriteRepositoryImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FavoriteGameLoader extends Loader<FavoriteContract.Presenter> {

    private FavoriteContract.Presenter mPresenter;

    FavoriteGameLoader(@NonNull Context context) {
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
        deliverResult(new FavoritePresenter(new FavoriteRepositoryImpl(getContext()), AndroidSchedulers.mainThread()));
    }

    @Override
    public void deliverResult(@Nullable FavoriteContract.Presenter data) {
        if (mPresenter == null) {
            mPresenter = data;
        }
        super.deliverResult(data);
    }
}
