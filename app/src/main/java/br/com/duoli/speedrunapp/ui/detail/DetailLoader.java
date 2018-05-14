package br.com.duoli.speedrunapp.ui.detail;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import br.com.duoli.speedrunapp.presenter.DetailContract;
import br.com.duoli.speedrunapp.presenter.DetailPresenter;
import br.com.duoli.speedrunapp.repository.LeaderboardRepositoryImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailLoader extends Loader<DetailContract.Presenter> {

    private DetailContract.Presenter mPresenter;

    public DetailLoader(@NonNull Context context) {
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
        deliverResult(new DetailPresenter(new LeaderboardRepositoryImpl(), AndroidSchedulers.mainThread()));
    }

    @Override
    public void deliverResult(@Nullable DetailContract.Presenter data) {
        if (mPresenter == null) {
            mPresenter = data;
        }
        super.deliverResult(data);
    }
}
