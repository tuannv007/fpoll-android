package com.framgia.fpoll.ui.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListFragment = new ArrayList<>();
    private String[] mTitle;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        mListFragment.addAll(fragments);
        mTitle = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment == null ? 0 : mListFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
