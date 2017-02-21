package com.framgia.fpoll.ui.authenication.resetpassword;

/**
 * Created by tuanbg on 2/21/17.
 */
public class ForgotPasswordActionHandler {
    private ForgotPasswordPresenter mListener;

    public ForgotPasswordActionHandler(ForgotPasswordPresenter listener) {
        this.mListener = listener;
    }

    public void resetPassword() {
        mListener.resetPassword();
    }
}
