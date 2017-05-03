package com.framgia.fpoll.ui.mainstart;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.framgia.fpoll.BR;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.ui.pollcreation.PollCreationActivity;

import static com.framgia.fpoll.ui.mainstart.NewMainViewModel.Tab.TAB_HOME;
import static com.framgia.fpoll.util.Constant.RequestCode.REQUEST_CREATE_POLL;

/**
 * Exposes the data to be used in the NewMain screen.
 */

public class NewMainViewModel extends BaseObservable implements NewMainContract.ViewModel {
    private static final int NUMBER_IMAGE_CHILD = 0;
    private NewMainContract.Presenter mPresenter;
    private final AppCompatActivity mActivity;
    private ObservableBoolean mIsBottomNavigationShow = new ObservableBoolean(true);
    private NewMainViewPagerAdapter mViewPagerAdapter;
    private int mPageLimit = 4;
    @Tab
    private int mCurrentTab = TAB_HOME;

    public NewMainViewModel(AppCompatActivity activity) {
        mActivity = activity;
        mViewPagerAdapter = new NewMainViewPagerAdapter(activity.getSupportFragmentManager());
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
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
    }

    @IntDef({ TAB_HOME, Tab.TAB_JOIN, Tab.TAB_FEED_BACK, Tab.TAB_PROFILE })
    public @interface Tab {
        int TAB_HOME = 0;
        int TAB_JOIN = 1;
        int TAB_FEED_BACK = 2;
        int TAB_PROFILE = 3;
    }
}
