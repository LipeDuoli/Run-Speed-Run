package br.com.duoli.speedrunapp.ui.main.latestruns;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.FragmentMainBinding;
import br.com.duoli.speedrunapp.presenter.LatestRunContract;
import br.com.duoli.speedrunapp.ui.detail.DetailActivity;
import br.com.duoli.sr4j.models.runs.Run;

public class LatestRunsFragment extends Fragment implements
        LatestRunContract.View,
        LatestRunAdapter.LatestRunOnClickListener,
        LoaderManager.LoaderCallbacks<LatestRunContract.Presenter> {

    private static final int LOADER_ID = 1000;

    private FragmentMainBinding mBinding;
    private LatestRunAdapter mRunAdapter;
    private LatestRunContract.Presenter mRunPresenter;

    public static LatestRunsFragment newInstance() {
        LatestRunsFragment fragment = new LatestRunsFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main,
                container,
                false);

        configureRecyclerView();
        configureSwipeRefresh();
        configureErrorMessages();
        return mBinding.getRoot();
    }

    private void configureErrorMessages() {
        mBinding.errorLayout.setErrorText(getString(R.string.error_load_latest_runs_text));
        mBinding.notFoundLayout.setNotFoundText(getString(R.string.not_found_latest_runs_text));
        mBinding.loadingLayout.setLoadingText(getString(R.string.loading_latest_runs_text));
    }

    private void configureSwipeRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRunPresenter.reloadData(false);
                if (mBinding.errorLayout.getRoot().isShown()){
                    mBinding.swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void configureRecyclerView() {
        mRunAdapter = new LatestRunAdapter(this);

        mBinding.recyclerView.setAdapter(mRunAdapter);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayRuns(List<Run> runList) {
        mRunAdapter.setRuns(runList);
        mBinding.recyclerView.setVisibility(View.VISIBLE);
        mBinding.swipeRefresh.setRefreshing(false);
        hideError();
        hideLoading();
        hideNotFound();
    }

    @Override
    public void displayLoading() {
        mBinding.loadingLayout.getRoot().setVisibility(View.VISIBLE);
        mBinding.recyclerView.setVisibility(View.GONE);
        hideNotFound();
        hideError();
    }

    @Override
    public void hideLoading() {
        mBinding.loadingLayout.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void displayNotFound() {
        mBinding.notFoundLayout.getRoot().setVisibility(View.VISIBLE);
        mBinding.recyclerView.setVisibility(View.GONE);
        hideError();
        hideLoading();
    }

    @Override
    public void hideNotFound() {
        mBinding.notFoundLayout.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void displayError() {
        mBinding.errorLayout.getRoot().setVisibility(View.VISIBLE);
        mBinding.recyclerView.setVisibility(View.GONE);
        hideLoading();
        hideNotFound();
    }

    @Override
    public void hideError() {
        mBinding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        mRunPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onClickLatestRun(Run run) {
        Intent detailIntent = DetailActivity.newInstance(getContext(),
                run.getGame().getId(), run.getCategory().getId());

        startActivity(detailIntent);
    }

    @NonNull
    @Override
    public Loader<LatestRunContract.Presenter> onCreateLoader(int id, @Nullable Bundle args) {
        return new LatestRunLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<LatestRunContract.Presenter> loader, LatestRunContract.Presenter data) {
        this.mRunPresenter = data;
        mRunPresenter.setView(this);
        mRunPresenter.loadData();
        mBinding.errorLayout.setPresenter(mRunPresenter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<LatestRunContract.Presenter> loader) {
        mRunPresenter.destroy();
        mRunPresenter = null;
    }
}
