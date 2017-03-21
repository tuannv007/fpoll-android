package com.framgia.fpoll.ui.authenication.resetpassword;

import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/21/17.
 */
public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private ForgotPasswordContract.View mView;
    private User mUser;
    private LoginRepository mRepository;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view, User user,
                                   LoginRepository repository) {
        mView = view;
        mUser = user;
        mRepository = repository;
    }

    @Override
    public void resetPassword() {
        if (mRepository == null) return;
        new UserValidation(mUser).validateResetPass(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                mView.showMessageError();
            }

            @Override
            public void onValidateSuccess() {
                mView.showDialog();
                mRepository.resetPassword(mUser.getEmail(), new DataCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        mView.showMessage(data);
                        mView.dismissDialog();
                    }

                    @Override
                    public void onError(String msg) {
                        mView.showMessage(msg);
                        mView.dismissDialog();
                    }
                });
            }
        });
    }

    public User getUser() {
        return mUser;
    }
}
