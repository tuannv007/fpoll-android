package com.framgia.fpoll.ui.polledition.editinformation;

import android.databinding.ObservableBoolean;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforPresenter implements EditInforContract.Presenter {
    private EditInforContract.View mView;
    private PollItem mPoll;
    private ObservableBoolean mIsLogin = new ObservableBoolean();

    public EditInforPresenter(EditInforContract.View view, PollItem pollItem,
            SharePreferenceUtil preference) {
        mView = view;
        mIsLogin.set(preference.isLogin());
        mPoll = pollItem;
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
