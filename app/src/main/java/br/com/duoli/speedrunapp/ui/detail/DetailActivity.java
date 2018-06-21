package br.com.duoli.speedrunapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.ActivityDetailBinding;
import br.com.duoli.speedrunapp.presenter.DetailContract;
import br.com.duoli.sr4j.models.categories.Category;
import br.com.duoli.sr4j.models.categories.CategoryTypes;
import br.com.duoli.sr4j.models.games.Game;

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
    private LeaderboardFragmentPagerAdapter mLeaderboardPagerAdapter;
    private List<String> categoryPositions = new ArrayList<>();

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void configureToolBar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void parseIntentExtras() {
        if (getIntent().hasExtra(GAME_ID)) {
            mGameId = getIntent().getStringExtra(GAME_ID);
        }
        if (getIntent().hasExtra(CATEGORY_ID)){
            mCategoryId = getIntent().getStringExtra(CATEGORY_ID);
        }
    }

    @Override
    public void displayGameInfo(Game game) {
        mBinding.setGame(game);
        mBinding.gameInfoFrame.setVisibility(View.VISIBLE);
        hideLoading();
        hideError();
        configureLeaderboardsTabsFor(game.getCategories());
        loadGameCoverBitmap(game.getAssets().getCoverLarge().getUri());
        moveToTabPosition(mCategoryId);
    }

    private void moveToTabPosition(String categoryId) {
        if (categoryId.isEmpty())
            return;

        for (int i = 0; i < categoryPositions.size(); i++){
            String id = categoryPositions.get(i);
            if (id.equals(categoryId)){
                mBinding.leaderboardViewpager.setCurrentItem(i);
                break;
            }
        }
    }

    private void loadGameCoverBitmap(String imageUrl) {
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                configureScreenColor(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                //do nothing
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                //do nothing
            }
        });
    }

    private void configureScreenColor(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                Palette.Swatch vibrantSwatch = p.getVibrantSwatch();
                Palette.Swatch darkSwatch = p.getDarkVibrantSwatch();
                Palette.Swatch darkMutedSwatch = p.getDarkMutedSwatch();
                if (vibrantSwatch != null) {
                    mBinding.toolbarLayout.setContentScrimColor(vibrantSwatch.getRgb());
                    mBinding.toolbarLayout.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.toolbarLayout.setExpandedTitleColor(vibrantSwatch.getBodyTextColor());
                    mBinding.toolbarLayout.setCollapsedTitleTextColor(vibrantSwatch.getBodyTextColor());

                    mBinding.gameYear.setTextColor(vibrantSwatch.getBodyTextColor());
                    mBinding.gamePlataform.setTextColor(vibrantSwatch.getBodyTextColor());

                    mBinding.leaderboardTab.setBackgroundColor(vibrantSwatch.getRgb());
                    mBinding.leaderboardTab.setTabTextColors(vibrantSwatch.getTitleTextColor(), vibrantSwatch.getBodyTextColor());
                }
                if (darkSwatch != null) {
                    mBinding.toolbarLayout.setStatusBarScrimColor(darkSwatch.getRgb());
                    mBinding.leaderboardTab.setSelectedTabIndicatorColor(darkSwatch.getRgb());
                } else if (darkMutedSwatch != null){
                    mBinding.toolbarLayout.setStatusBarScrimColor(darkMutedSwatch.getRgb());
                    mBinding.leaderboardTab.setSelectedTabIndicatorColor(darkMutedSwatch.getRgb());
                }
            }
        });
    }

    private void configureLeaderboardsTabsFor(List<Category> categories) {
        for (Category category : categories) {
            if (category.getType() == CategoryTypes.PER_GAME) {
                mLeaderboardPagerAdapter.addFragment(
                        LeaderboardFragment.newInstance(mGameId, category.getId()),
                        category.getName());
                categoryPositions.add(category.getId());
            }
        }
        mLeaderboardPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayLoading() {
        mBinding.loadingLayout.getRoot().setVisibility(View.VISIBLE);
        mBinding.gameInfoFrame.setVisibility(View.GONE);
        hideError();
    }

    @Override
    public void hideLoading() {
        mBinding.loadingLayout.getRoot().setVisibility(View.GONE);
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
        mBinding.errorLayout.getRoot().setVisibility(View.VISIBLE);
        mBinding.gameInfoFrame.setVisibility(View.GONE);
        hideLoading();
    }

    @Override
    public void hideError() {
        mBinding.errorLayout.getRoot().setVisibility(View.GONE);
    }

    public static Intent newInstance(@NonNull Context context,
                                     @NonNull String gameId,
                                     @Nullable String categoryId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(GAME_ID, gameId);
        if (categoryId != null){
            intent.putExtra(CATEGORY_ID, categoryId);
        }
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
