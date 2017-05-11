package com.framgia.fpoll.ui.mainstart;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.framgia.fpoll.BR;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.ui.feedback.FeedbackFragment;
import com.framgia.fpoll.ui.history.HistoryFragment;
import com.framgia.fpoll.ui.joinpoll.JoinPollFragment;
import com.framgia.fpoll.ui.pollcreation.PollCreationActivity;
import com.framgia.fpoll.ui.profile.ProfileFragment;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.LanguageUtil;
import com.framgia.fpoll.util.SharePreferenceUtil;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.ui.mainstart.NewMainViewModel.Tab.TAB_HOME;
import static com.framgia.fpoll.util.Constant.RequestCode.REQUEST_CREATE_POLL;

/**
 * Exposes the data to be used in the NewMain screen.
 */

public class NewMainViewModel extends BaseObservable implements NewMainContract.ViewModel {
    private static final int NUMBER_IMAGE_CHILD = 0;
    private static final String LANG_EN = "en";
    private static final String LANG_VN = "vi";
    private NewMainContract.Presenter mPresenter;
    private final AppCompatActivity mActivity;
    private ObservableBoolean mIsBottomNavigationShow = new ObservableBoolean(true);
    private NewMainViewPagerAdapter mViewPagerAdapter;
    private int mPageLimit = 4;
    @Tab
    private int mCurrentTab = TAB_HOME;

    public NewMainViewModel(AppCompatActivity activity) {
        mActivity = activity;
        initViewPager();
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HistoryFragment.newInstance());
        fragments.add(JoinPollFragment.newInstance());
        fragments.add(FeedbackFragment.newInstance());
        fragments.add(ProfileFragment.newInstance());
        mViewPagerAdapter =
                new NewMainViewPagerAdapter(mActivity.getSupportFragmentManager(), fragments);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        if (SharePreferenceUtil.getIntances(mActivity) == null) return;
        String language = SharePreferenceUtil.getIntances(mActivity).getLanguage();
        switch (language) {
            case LANG_EN:
                LanguageUtil.changeLang(Constant.Language.LANGUAGE_EN, mActivity);
                break;
            case LANG_VN:
                LanguageUtil.changeLang(Constant.Language.LANGUAGE_VN, mActivity);
                break;
            default:
                LanguageUtil.changeLang(Constant.Language.LANGUAGE_JP, mActivity);
                break;
        }
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(NewMainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBottomBarClick(View view) {
        switch (view.getId()) {
            case R.id.relative_home:
                setCurrentTab(TAB_HOME);
                break;
            case R.id.relative_join_poll:
                setCurrentTab(Tab.TAB_JOIN);
                break;
            case R.id.relative_feedback:
                setCurrentTab(Tab.TAB_FEED_BACK);
                break;
            case R.id.relative_profile:
                setCurrentTab(Tab.TAB_PROFILE);
                break;
            default:
                break;
        }
        LinearLayout container = (LinearLayout) view.getParent();
        for (int i = 0; i < container.getChildCount(); i++) {
            RelativeLayout v = (RelativeLayout) container.getChildAt(i);
            if (v.getChildCount() != 0) {
                v.getChildAt(NUMBER_IMAGE_CHILD).setSelected(false);
            }
        }

        if (view instanceof RelativeLayout) {
            ((RelativeLayout) view).getChildAt(NUMBER_IMAGE_CHILD).setSelected(true);
        }
    }

    @Override
    public void onStartPollCreate() {
        mActivity.startActivityForResult(PollCreationActivity.getIntent(mActivity, new PollItem()),
                REQUEST_CREATE_POLL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK || requestCode != REQUEST_CREATE_POLL) return;
        setCurrentTab(TAB_HOME);
        Fragment fragment = mViewPagerAdapter.getItem(TAB_HOME);
        if (fragment != null && fragment instanceof HistoryFragment) {
            ((HistoryFragment) fragment).updatePollHistory();
        }
    }

    @Override
    public void hideBottomNavigation() {
        mIsBottomNavigationShow.set(false);
    }

    @Override
    public void showBottomNavigation() {
        mIsBottomNavigationShow.set(true);
    }

    public ObservableBoolean getIsBottomNavigationShow() {
        return mIsBottomNavigationShow;
    }

    public NewMainViewPagerAdapter getViewPagerAdapter() {
        return mViewPagerAdapter;
    }

    public int getPageLimit() {
        return mPageLimit;
    }

    @Bindable
    public int getCurrentTab() {
        return mCurrentTab;
    }

    public void setCurrentTab(int currentTab) {
        mCurrentTab = currentTab;
        notifyPropertyChanged(BR.currentTab);
        if (mViewPagerAdapter != null) mViewPagerAdapter.notifyDataSetChanged();
    }

    @IntDef({ TAB_HOME, Tab.TAB_JOIN, Tab.TAB_FEED_BACK, Tab.TAB_PROFILE })
    public @interface Tab {
        int TAB_HOME = 0;
        int TAB_JOIN = 1;
        int TAB_FEED_BACK = 2;
        int TAB_PROFILE = 3;
    }
}
