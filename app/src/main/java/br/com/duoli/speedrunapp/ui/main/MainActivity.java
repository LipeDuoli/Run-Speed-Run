package br.com.duoli.speedrunapp.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.com.duoli.speedrunapp.R;
import br.com.duoli.speedrunapp.databinding.ActivityMainBinding;
import br.com.duoli.speedrunapp.ui.main.favorites.FavoriteGamesFragment;
import br.com.duoli.speedrunapp.ui.main.games.GamesFragment;
import br.com.duoli.speedrunapp.ui.main.latestruns.LatestRunsFragment;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        configureToolbar();
        configureNavigationDrawer();
        configureRxErrorHandle();

        if (savedInstanceState == null)
            initLatestRunsFragment();
    }

    private void configureRxErrorHandle() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG,"Error on clear disposable: " + throwable.toString());
            }
        });
    }

    private void configureToolbar() {
        mToolbar = mBinding.toolbar;
        setSupportActionBar(mToolbar);
    }

    private void configureNavigationDrawer() {
        mDrawer = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_latest:
                initLatestRunsFragment();
                break;
            case R.id.nav_games:
                initGamesFragment();
                break;
            case R.id.nav_favorites:
                initFavoriteGamesFragment();
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initLatestRunsFragment() {
        LatestRunsFragment runsFragment = LatestRunsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, runsFragment)
                .commit();

        changeToolbarTitle(getString(R.string.nav_latest));
    }

    private void initGamesFragment() {
        GamesFragment gamesFragment = GamesFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, gamesFragment)
                .commit();

        changeToolbarTitle(getString(R.string.nav_games));
    }

    private void initFavoriteGamesFragment() {
        FavoriteGamesFragment favoriteGamesFragment = FavoriteGamesFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame, favoriteGamesFragment)
                .commit();

        changeToolbarTitle(getString(R.string.nav_favorites));
    }

    private void changeToolbarTitle(String title) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setTitle(title);
        }
    }
}
