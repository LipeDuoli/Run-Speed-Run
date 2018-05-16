package br.com.duoli.speedrunapp.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class LeaderboardFragment extends Fragment {

    public static final String GAME_ID = "gameId";
    public static final String CATEGORY_ID = "categoryId";
    private String mGameId;
    private String mCategoryId;

    public static LeaderboardFragment newInstance(String gameId, String categoryId){
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
}
