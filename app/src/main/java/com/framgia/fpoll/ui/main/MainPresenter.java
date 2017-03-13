package com.framgia.fpoll.ui.main;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private User mUser = new User();
    private LoginRepository mRepository;
    private String mToken = ""; // Token after login

    public MainPresenter(MainContract.View view, @NonNull LoginRepository repository) {
        mView = view;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void logout() {
        if (mRepository == null) return;
        mRepository.logout(mToken, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    public User getUser() {
        return mUser;
    }
}
