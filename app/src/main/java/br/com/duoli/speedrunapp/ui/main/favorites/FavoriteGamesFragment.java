package br.com.duoli.speedrunapp.ui.main.favorites;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.FragmentMainBinding;
import br.com.duoli.speedrunapp.model.FavoriteGame;
import br.com.duoli.speedrunapp.presenter.FavoriteContract;
import br.com.duoli.speedrunapp.ui.detail.DetailActivity;

public class FavoriteGamesFragment extends Fragment implements
        FavoriteContract.View,
        FavoriteGameAdapter.GamesOnClickListener,
        LoaderManager.LoaderCallbacks<FavoriteContract.Presenter> {

    private static final int LOADER_ID = 3000;

    private FragmentMainBinding mBinding;
    private FavoriteGameAdapter mGameAdapter;
    private FavoriteContract.Presenter mGamePresenter;

    public static FavoriteGamesFragment newInstance() {
        FavoriteGamesFragment fragment = new FavoriteGamesFragment();
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
        mBinding.errorLayout.setErrorText(getString(R.string.error_load_favorites_text));
        mBinding.notFoundLayout.setNotFoundText(getString(R.string.not_found_favorites_text));
        mBinding.loadingLayout.setLoadingText(getString(R.string.loading_favorites_text));
    }

    private void configureSwipeRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGamePresenter.reloadData(false);
                if (mBinding.errorLayout.getRoot().isShown()) {
                    mBinding.swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void configureRecyclerView() {
        mGameAdapter = new FavoriteGameAdapter(this);

        mBinding.recyclerView.setAdapter(mGameAdapter);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void displayGames(List<FavoriteGame> gameList) {
        mGameAdapter.setRuns(gameList);
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
        mBinding.swipeRefresh.setRefreshing(false);
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
        mGamePresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onClickGame(FavoriteGame game) {
        Intent detailIntent = DetailActivity.newInstance(getContext(),
                game.getGameId());

        startActivity(detailIntent);
    }

    @NonNull
    @Override
    public Loader<FavoriteContract.Presenter> onCreateLoader(int id, @Nullable Bundle args) {
        return new FavoriteGameLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<FavoriteContract.Presenter> loader, FavoriteContract.Presenter data) {
        this.mGamePresenter = data;
        mGamePresenter.setView(this);
        mGamePresenter.loadData();
        mBinding.errorLayout.setPresenter(mGamePresenter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<FavoriteContract.Presenter> loader) {
        mGamePresenter.destroy();
        mGamePresenter = null;
    }
}
