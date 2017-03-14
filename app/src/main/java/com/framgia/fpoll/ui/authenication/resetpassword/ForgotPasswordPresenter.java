package com.framgia.fpoll.ui.authenication.resetpassword;

import android.util.Log;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.resetpassword.ResetDataRepository;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/21/17.
 */
public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private ForgotPasswordContract.View mView;
    private User mUser;
    private ResetDataRepository mRepository;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view, User user,
                                   ResetDataRepository repository) {
        mView = view;
        mUser = user;
        mRepository = repository;
    }

    public void getAllData(@NonNull User user) {
        mView.showDialog();
        mRepository.resetPassword(user, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                mView.onSuccess(data);
                mView.dismissDialog();
            }

            @Override
            public void onError(String message) {
                mView.onError(message);
                mView.dismissDialog();
            }
        });
    }

    @Override
    public void resetPassword() {
        new UserValidation(mUser).validateResetPass(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                mView.showMessageError();
            }

            @Override
            public void onValidateSuccess() {
                getAllData(mUser);
            }
        });
    }

    public User getUser() {
        return mUser;
    }
}
