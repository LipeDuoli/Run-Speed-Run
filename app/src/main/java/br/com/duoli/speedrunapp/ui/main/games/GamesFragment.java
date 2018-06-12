package br.com.duoli.speedrunapp.ui.main.games;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.FragmentMainBinding;
import br.com.duoli.speedrunapp.presenter.GamesContract;
import br.com.duoli.speedrunapp.tools.EndlessRecyclerViewScrollListener;
import br.com.duoli.speedrunapp.ui.detail.DetailActivity;
import br.com.duoli.sr4j.models.games.Game;

public class GamesFragment extends Fragment implements
        GamesContract.View,
        GameAdapter.GamesOnClickListener,
        LoaderManager.LoaderCallbacks<GamesContract.Presenter> {

    private static final int LOADER_ID = 2000;
    private static final String EXTRA_GAME_NAME = "extraGameName";

    private FragmentMainBinding mBinding;
    private GameAdapter mGameAdapter;
    private GamesContract.Presenter mGamePresenter;
    private EndlessRecyclerViewScrollListener mEndlessScrollListener;
    private String gameName = "";

    public static GamesFragment newInstance(String gameName) {
        GamesFragment fragment = new GamesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_GAME_NAME, gameName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        gameName = getArguments().getString(EXTRA_GAME_NAME);
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
        return mBinding.getRoot();
    }

    private void configureSwipeRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGamePresenter.reloadData(false);
                if (mBinding.errorLayout.getRoot().isShown()){
                    mBinding.swipeRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void configureRecyclerView() {
        mGameAdapter = new GameAdapter(this);

        mBinding.recyclerView.setAdapter(mGameAdapter);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);

        mEndlessScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mGamePresenter.loadMore();
            }
        };

        mBinding.recyclerView.addOnScrollListener(mEndlessScrollListener);
    }

    @Override
    public void displayGames(List<Game> gameList) {
        mGameAdapter.setGames(gameList);
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
        mGamePresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onClickGame(Game game) {
        Intent detailIntent = DetailActivity.newInstance(getContext(),
                game.getId());

        startActivity(detailIntent);
    }

    @NonNull
    @Override
    public Loader<GamesContract.Presenter> onCreateLoader(int id, @Nullable Bundle args) {
        return new GameLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<GamesContract.Presenter> loader, GamesContract.Presenter data) {
        this.mGamePresenter = data;
        mGamePresenter.setView(this);
        mGamePresenter.loadData(gameName);
        mBinding.errorLayout.setPresenter(mGamePresenter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<GamesContract.Presenter> loader) {
        mGamePresenter.destroy();
        mGamePresenter = null;
    }
}
