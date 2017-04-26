package com.framgia.fpoll.ui.polledition.editinformation;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.view.View;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforPresenter implements EditInforContract.Presenter {
    private EditInforContract.View mView;
    private PollItem mPoll;
    private ObservableBoolean mIsLogin = new ObservableBoolean();
    private ObservableInt mUserNameVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mEmailVisibility = new ObservableInt(View.VISIBLE);

    public EditInforPresenter(EditInforContract.View view, PollItem pollItem,
            SharePreferenceUtil preference) {
        mView = view;
        mIsLogin.set(preference.isLogin());
        mPoll = pollItem;
        initData(preference, pollItem);
    }

    private void initData(SharePreferenceUtil preference, PollItem poll) {
        User user = preference.getUser();
        if (user == null || poll == null || poll.getUser() == null) return;
        User userPoll = poll.getUser();
        mUserNameVisibility.set(
                (!TextUtils.isEmpty(user.getUsername()) && TextUtils.equals(user.getUsername(),
                        userPoll.getUsername())) ? View.GONE : View.VISIBLE);
        mEmailVisibility.set(
                (!TextUtils.isEmpty(user.getEmail()) && TextUtils.equals(user.getEmail(),
                        userPoll.getEmail())) ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }

    public ObservableInt getUserNameVisibility() {
        return mUserNameVisibility;
    }

    public ObservableInt getEmailVisibility() {
        return mEmailVisibility;
    }
}
