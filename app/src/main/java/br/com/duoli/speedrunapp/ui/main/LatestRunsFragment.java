package br.com.duoli.speedrunapp.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.FragmentMainBinding;
import br.com.duoli.speedrunapp.presenter.LatestRunContract;
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
        return mBinding.getRoot();
    }

    private void configureRecyclerView() {
        mRunAdapter = new LatestRunAdapter(getContext(), this);

        mBinding.recyclerView.setAdapter(mRunAdapter);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayRuns(List<Run> runList) {
        mRunAdapter.setRuns(runList);
    }

    @Override
    public void showLoadingLayout() {
        mBinding.loadingLayout.setVisibility(View.VISIBLE);
        mBinding.recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingLayout() {
        mBinding.loadingLayout.setVisibility(View.GONE);
        mBinding.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        mRunPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onClickLatestRun(Run run) {
        //TODO go to run detail
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
        mRunPresenter.loadLatestRuns();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<LatestRunContract.Presenter> loader) {
        mRunPresenter.destroy();
        mRunPresenter = null;
    }
}
