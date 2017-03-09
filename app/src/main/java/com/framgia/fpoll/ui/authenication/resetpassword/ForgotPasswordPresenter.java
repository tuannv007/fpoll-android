package com.framgia.fpoll.ui.authenication.resetpassword;

import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/21/17.
 */
public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private ForgotPasswordContract.View mView;
    private User mUser;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view, User user) {
        this.mView = view;
        mUser = user;
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
                // TODO: 2/21/17 request server send email
            }
        });
    }

    public User getUser() {
        return mUser;
    }
}
