package br.com.duoli.speedrunapp.ui.main.latestruns;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import br.com.duoli.speedrunapp.presenter.LatestRunContract;
import br.com.duoli.speedrunapp.presenter.LatestRunPresenter;
import br.com.duoli.speedrunapp.repository.RunsRepositoryImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LatestRunLoader extends Loader<LatestRunContract.Presenter> {

    private LatestRunContract.Presenter mPresenter;

    LatestRunLoader(@NonNull Context context) {
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
        deliverResult(new LatestRunPresenter(new RunsRepositoryImpl(), AndroidSchedulers.mainThread()));
    }

    @Override
    public void deliverResult(@Nullable LatestRunContract.Presenter data) {
        if (mPresenter == null){
            mPresenter = data;
        }
        super.deliverResult(data);
    }
}
