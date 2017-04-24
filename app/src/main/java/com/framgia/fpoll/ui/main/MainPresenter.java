package com.framgia.fpoll.ui.main;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.data.source.remote.settings.SettingRepository;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private final ObservableBoolean mIsLogin = new ObservableBoolean();
    private ObservableField<User> mUser = new ObservableField<>();
    private LoginRepository mRepository;
    private SettingRepository mSettingRepository;
    private SharePreferenceUtil mPreference;

    public MainPresenter(MainContract.View view, @NonNull LoginRepository repository,
            SettingRepository settingRepository, @NonNull SharePreferenceUtil preference) {
        mView = view;
        mRepository = repository;
        mSettingRepository = settingRepository;
        mPreference = preference;
        setInformation();
        mView.start();
    }

    @Override
    public void updateProfile() {
        if (mView != null) mView.startUiProfileEdition();
    }

    @Override
    public void startUIPollCreation() {
        if (mView != null) mView.startUIPollCreation();
    }

    @Override
    public void onUserUpdate() {
        mUser.set(mPreference.getUser());
    }

    @Override
    public void logout() {
        if (mRepository == null) return;
        mView.showProgressDialog();
        mRepository.logout(mUser.get().getToken(), new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
                mView.clearDataHome();
                mPreference.writeUser(null);
                mPreference.writeLogin(false);
                setInformation();
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
        mUser.set(mPreference.getUser());
        mIsLogin.set(mPreference.isLogin());
    }

    @Override
    public void changeLanguage(String lang) {
        mSettingRepository.changeLanguage(lang, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                mView.changeLangStatus(data.getData().toString());
            }

            @Override
            public void onError(String msg) {
                mView.changeLangStatus(msg);
            }
        });
    }

    public ObservableField<User> getUser() {
        return mUser;
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
