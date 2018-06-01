package br.com.duoli.speedrunapp.ui.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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
import android.widget.Toast;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.FragmentLeaderboardBinding;
import br.com.duoli.speedrunapp.presenter.LeaderboardContract;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public class LeaderboardFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<LeaderboardContract.Presenter>,
        LeaderboardAdapter.LeaderboardOnClickListener,
        LeaderboardContract.View {

    private static final int LOADER_ID = 6000;

    public static final String GAME_ID = "gameId";
    public static final String CATEGORY_ID = "categoryId";
    private static final String TAG = LeaderboardFragment.class.getSimpleName();
    private String mGameId;
    private String mCategoryId;
    private FragmentLeaderboardBinding mBinding;
    private LeaderboardAdapter mLeaderboardAdapter;
    private LeaderboardContract.Presenter mLeaderboardPresenter;

    public static LeaderboardFragment newInstance(String gameId, String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(GAME_ID, gameId);
        bundle.putString(CATEGORY_ID, categoryId);
        LeaderboardFragment leaderboardFragment = new LeaderboardFragment();
        leaderboardFragment.setArguments(bundle);
        return leaderboardFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameId = getArguments().getString(GAME_ID);
        mCategoryId = getArguments().getString(CATEGORY_ID);
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
                R.layout.fragment_leaderboard,
                container,
                false);

        configureRecyclerView();
        configureErrorTexts();
        return mBinding.getRoot();

    }

    private void configureErrorTexts() {
        mBinding.errorLayout.setErrorText(getString(R.string.error_load_leaderboards_text));
        mBinding.notFoundLayout.setNotFoundText(getString(R.string.not_found_leaderboard_text));
        mBinding.loadingLayout.setLoadingText(getString(R.string.loading_leaderboards_text));
    }

    private void configureRecyclerView() {
        mLeaderboardAdapter = new LeaderboardAdapter(this);

        mBinding.recyclerView.setAdapter(mLeaderboardAdapter);
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClickLeaderboard(String videoUrl) {
        Uri webpage = Uri.parse(videoUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void displayLeaderboard(Leaderboard leaderboard) {
        mLeaderboardAdapter.setLeaderboard(leaderboard);
        mBinding.recyclerView.setVisibility(View.VISIBLE);
        hideLoading();
        hideError();
        hideNotFound();
    }

    @Override
    public void displayLoading() {
        mBinding.recyclerView.setVisibility(View.INVISIBLE);
        mBinding.loadingLayout.getRoot().setVisibility(View.VISIBLE);
        hideNotFound();
        hideError();
    }

    @Override
    public void hideLoading() {
        mBinding.loadingLayout.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void displayNotFound() {
        mBinding.recyclerView.setVisibility(View.INVISIBLE);
        mBinding.notFoundLayout.getRoot().setVisibility(View.VISIBLE);
        hideLoading();
    }

    @Override
    public void hideNotFound() {
        mBinding.notFoundLayout.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void displayError() {
        mBinding.recyclerView.setVisibility(View.INVISIBLE);
        mBinding.errorLayout.getRoot().setVisibility(View.VISIBLE);
        hideLoading();
    }

    @Override
    public void hideError() {
        mBinding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public Loader<LeaderboardContract.Presenter> onCreateLoader(int id, @Nullable Bundle args) {
        return new LeaderboardLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<LeaderboardContract.Presenter> loader, LeaderboardContract.Presenter data) {
        this.mLeaderboardPresenter = data;
        mLeaderboardPresenter.setView(this);
        mLeaderboardPresenter.loadData(mGameId, mCategoryId);
        mBinding.errorLayout.setPresenter(mLeaderboardPresenter);
        mBinding.setFavoritePresenter(mLeaderboardPresenter);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<LeaderboardContract.Presenter> loader) {
        //do nothing
    }

    @Override
    public void showFavoriteAdded() {
        Toast.makeText(getContext(), getString(R.string.favorite_saved_msg), Toast.LENGTH_SHORT).show();
        mBinding.favoriteGameBtn.setImageResource(R.drawable.ic_star);
    }

    @Override
    public void showErroAddFavorite() {
        Toast.makeText(getContext(), getString(R.string.favorite_save_error_msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFavoriteRemoved() {
        Toast.makeText(getContext(), getString(R.string.favorite_removed_msg), Toast.LENGTH_SHORT).show();
        mBinding.favoriteGameBtn.setImageResource(R.drawable.ic_star_border);
    }

    @Override
    public void showErroRemoveFavorite() {
        Toast.makeText(getContext(), getString(R.string.favorite_remove_error_msg), Toast.LENGTH_SHORT).show();
    }
}
