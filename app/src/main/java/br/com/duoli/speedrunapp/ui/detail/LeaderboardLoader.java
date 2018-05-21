package br.com.duoli.speedrunapp.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import br.com.duoli.speedrunapp.presenter.LeaderboardContract;
import br.com.duoli.speedrunapp.presenter.LeaderboardPresenter;
import br.com.duoli.speedrunapp.repository.LeaderboardRepositoryImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LeaderboardLoader extends Loader<LeaderboardContract.Presenter> {

    private LeaderboardContract.Presenter mPresenter;

    public LeaderboardLoader(@NonNull Context context) {
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
        deliverResult(new LeaderboardPresenter(new LeaderboardRepositoryImpl(), AndroidSchedulers.mainThread()));
    }

    @Override
    public void deliverResult(@Nullable LeaderboardContract.Presenter data) {
        if (mPresenter == null) {
            mPresenter = data;
        }
        super.deliverResult(data);
    }
}
