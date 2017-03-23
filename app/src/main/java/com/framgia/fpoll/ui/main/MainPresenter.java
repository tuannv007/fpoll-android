package com.framgia.fpoll.ui.main;

import android.databinding.ObservableBoolean;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private User mUser;
    private LoginRepository mRepository;
    private SharePreferenceUtil mPreference;
    private final ObservableBoolean mIsLogin = new ObservableBoolean();

    public MainPresenter(MainContract.View view, @NonNull LoginRepository repository,
                         @NonNull SharePreferenceUtil preference) {
        mView = view;
        mRepository = repository;
        mPreference = preference;
        setInformation();
        mView.start();
    }

    @Override
    public void updateProfile() {
        if (mView != null) mView.startUiProfileEdition();
    }

    @Override
    public void logout() {
        if (mRepository == null) return;
        mView.showProgressDialog();
        mRepository.logout(mUser.getToken(), new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
                mPreference.writeUser(null);
                mPreference.writeLogin(false);
                setInformation();
                mView.showMessage(data);
                mView.hideProgressDialog();
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideProgressDialog();
            }
        });
    }

    @Override
    public void setInformation() {
        mUser = mPreference.getUser();
        mIsLogin.set(mPreference.isLogin());
    }

    public User getUser() {
        return mUser;
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
