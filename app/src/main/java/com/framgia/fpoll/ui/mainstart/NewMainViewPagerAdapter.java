package com.framgia.fpoll.ui.mainstart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.framgia.fpoll.ui.feedback.FeedbackFragment;
import com.framgia.fpoll.ui.history.HistoryFragment;
import com.framgia.fpoll.ui.joinpoll.JoinPollFragment;
import com.framgia.fpoll.ui.profile.ProfileFragment;

import static com.framgia.fpoll.ui.mainstart.NewMainViewModel.Tab.TAB_FEED_BACK;
import static com.framgia.fpoll.ui.mainstart.NewMainViewModel.Tab.TAB_HOME;
import static com.framgia.fpoll.ui.mainstart.NewMainViewModel.Tab.TAB_PROFILE;
import static com.framgia.fpoll.ui.mainstart.NewMainViewModel.Tab.TAB_JOIN;

/**
 * Created by framgia on 28/04/2017.
 */

public class NewMainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int TAB_NUMBER = 4;

    public NewMainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_HOME:
                return HistoryFragment.newInstance();
            case TAB_JOIN:
                return JoinPollFragment.newInstance();
            case TAB_FEED_BACK:
                return FeedbackFragment.newInstance();
            case TAB_PROFILE:
                return ProfileFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }
}
