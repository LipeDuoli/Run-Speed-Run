package br.com.duoli.speedrunapp.ui.main;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import br.com.duoli.speedrunapp.presenter.LatestRunContract;
import br.com.duoli.speedrunapp.presenter.LatestRunPresenter;
import br.com.duoli.speedrunapp.repository.RunsRepositoryImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LatestRunLoader extends Loader<LatestRunContract.Presenter> {

    LatestRunContract.Presenter mPresenter;
    private LatestRunContract.View mView;

    public LatestRunLoader(@NonNull Context context, LatestRunContract.View view) {
        super(context);
        mView = view;
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
        deliverResult(new LatestRunPresenter(mView, new RunsRepositoryImpl(), AndroidSchedulers.mainThread()));
    }

    @Override
    public void deliverResult(@Nullable LatestRunContract.Presenter data) {
        if (mPresenter == null){
            mPresenter = data;
        }
        super.deliverResult(data);
    }
}
