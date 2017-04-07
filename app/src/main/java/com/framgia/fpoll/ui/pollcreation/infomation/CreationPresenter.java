package com.framgia.fpoll.ui.pollcreation.infomation;

import android.databinding.ObservableBoolean;
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
    private ObservableBoolean mIsLogin = new ObservableBoolean();

    public CreationPresenter(CreationContract.View view, PollItem poll,
            SharePreferenceUtil preference) {
        mView = view;
        mPoll = poll;
        mPreference = preference;
        mView.start();
        mIsLogin.set(mPreference.isLogin());
        updateInformationPoll();
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

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
