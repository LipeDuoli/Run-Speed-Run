package br.com.duoli.speedrunapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.ActivityDetailBinding;
import br.com.duoli.speedrunapp.presenter.DetailContract;
import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.games.Game;

public class DetailActivity extends AppCompatActivity implements
        DetailContract.View,
        LoaderManager.LoaderCallbacks<DetailContract.Presenter> {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private static final int LOADER_ID = 5000;
    public static final String GAME_ID = "gameId";

    private ActivityDetailBinding mBinding;
    private String mGameId = "";
    private DetailContract.Presenter mDetailPresenter;
    private LeaderboardFragmentPagerAdapter mLeaderboardPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        configureToolBar();
        parseIntentExtras();
        initViewPager();
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

    }

    private void initViewPager() {
        mLeaderboardPagerAdapter = new LeaderboardFragmentPagerAdapter(getSupportFragmentManager());
        mBinding.leaderboardViewpager
                .setAdapter(mLeaderboardPagerAdapter);

        mBinding.leaderboardTab.setupWithViewPager(mBinding.leaderboardViewpager);
    }

    @Override
    protected void onDestroy() {
        mDetailPresenter.destroy();
        super.onDestroy();
    }

    private void configureToolBar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void parseIntentExtras() {
        if (getIntent().hasExtra(GAME_ID)) {
            mGameId = getIntent().getStringExtra(GAME_ID);
        }
    }

    @Override
    public void displayGameInfo(Game game) {
        mBinding.setGame(game);
        mBinding.gameInfoFrame.setVisibility(View.VISIBLE);
        hideLoading();
        hideError();
        configureLeaderboardsTabsFor(game.getCategories());
    }

    private void configureLeaderboardsTabsFor(List<Category> categories) {
        for(Category category : categories){
            mLeaderboardPagerAdapter.addFragment(
                    LeaderboardFragment.newInstance(mGameId, category.getId()),
                    category.getName());
        }
        mLeaderboardPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayLoading() {
        mBinding.loadingLayout.setVisibility(View.VISIBLE);
        mBinding.gameInfoFrame.setVisibility(View.GONE);
        mBinding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        mBinding.loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void displayNotFound() {
        Toast.makeText(this, getString(R.string.game_not_found), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void hideNotFound() {
        //not used on this screen
    }

    @Override
    public void displayError() {
        mBinding.loadingLayout.setVisibility(View.GONE);
        mBinding.gameInfoFrame.setVisibility(View.GONE);
        mBinding.errorLayout.getRoot().setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mBinding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    public static Intent newInstance(Context context, String gameId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(GAME_ID, gameId);
        return intent;
    }

    @NonNull
    @Override
    public Loader<DetailContract.Presenter> onCreateLoader(int id, @Nullable Bundle args) {
        return new DetailLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<DetailContract.Presenter> loader, DetailContract.Presenter data) {
        this.mDetailPresenter = data;
        mDetailPresenter.setView(this);
        mDetailPresenter.loadData(mGameId);
        mBinding.errorLayout.setPresenter(mDetailPresenter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<DetailContract.Presenter> loader) {

    }
}
