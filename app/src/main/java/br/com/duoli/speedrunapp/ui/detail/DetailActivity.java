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

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.ActivityDetailBinding;
import br.com.duoli.speedrunapp.presenter.DetailContract;
import br.com.duoli.sr4j.models.leaderboards.Leaderboard;

public class DetailActivity extends AppCompatActivity implements
        DetailContract.View,
        LoaderManager.LoaderCallbacks<DetailContract.Presenter> {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private static final int LOADER_ID = 5000;
    public static final String GAME_ID = "gameId";
    public static final String CATEGORY_ID = "categoryId";

    private ActivityDetailBinding mBinding;
    private String mGameId = "";
    private String mCategoryId = "";
    private DetailContract.Presenter mDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        configureToolBar();
        parseIntentExtras();
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

    }

    private void configureToolBar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void parseIntentExtras() {
        if (getIntent().hasExtra(GAME_ID)) {
            mGameId = getIntent().getStringExtra(GAME_ID);
        }
        if (getIntent().hasExtra(CATEGORY_ID)) {
            mCategoryId = getIntent().getStringExtra(CATEGORY_ID);
        }
    }

    @Override
    public void displayLeaderboard(Leaderboard leaderboard) {
        mBinding.setLeaderboard(leaderboard);
    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void displayNotFound() {

    }

    @Override
    public void hideNotFound() {

    }

    @Override
    public void displayError() {

    }

    @Override
    public void hideError() {


    }

    public static Intent newInstance(Context context, String gameId, String categoryId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(GAME_ID, gameId);
        intent.putExtra(CATEGORY_ID, categoryId);
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
        mDetailPresenter.loadData(mGameId, mCategoryId);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<DetailContract.Presenter> loader) {

    }
}
