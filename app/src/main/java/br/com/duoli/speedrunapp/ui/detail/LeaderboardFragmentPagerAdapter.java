package br.com.duoli.speedrunapp.ui.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<LeaderboardFragment> mFragmentList;
    private List<String> mTitleList;

    public LeaderboardFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    public void addFragment(LeaderboardFragment fragment, String title){
        mFragmentList.add(fragment);
        mTitleList.add(title);
    }
}
