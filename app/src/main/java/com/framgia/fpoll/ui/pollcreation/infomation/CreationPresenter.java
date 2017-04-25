package com.framgia.fpoll.ui.pollcreation.infomation;

import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.view.View;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by framgia on 20/02/2017.
 */
public class CreationPresenter implements CreationContract.Presenter {
    private CreationContract.View mView;
    private PollItem mPoll;
    private SharePreferenceUtil mPreference;
    private ObservableInt mUserNameVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mEmailVisibility = new ObservableInt(View.VISIBLE);

    public CreationPresenter(CreationContract.View view, PollItem poll,
            SharePreferenceUtil preference) {
        mView = view;
        mPoll = poll;
        mPreference = preference;
        mView.start();
        initData(preference, poll);
        updateInformationPoll();
    }

    private void initData(SharePreferenceUtil preference, PollItem poll) {
        User user = preference.getUser();
        if (user == null) return;
        mUserNameVisibility.set(
                (preference.isLogin() && !TextUtils.isEmpty(user.getUsername())) ? View.GONE
                        : View.VISIBLE);
        mEmailVisibility.set(
                (preference.isLogin() && !TextUtils.isEmpty(user.getEmail())) ? View.GONE
                        : View.VISIBLE);
    }

    private void updateInformationPoll() {
        if (mPreference == null || mPoll == null) return;
        User user = mPreference.getUser();
        if (user != null) {
            mPoll.setUser(user);
        }
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }

    public ObservableInt getUserNameVisibility() {
        return mUserNameVisibility;
    }

    public ObservableInt getEmailVisibility() {
        return mEmailVisibility;
    }
}
