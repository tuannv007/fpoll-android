package com.framgia.fpoll.ui.authenication.register;

/**
 * Created by tuanbg on 2/17/17.
 */
public class RegisterItemActionHandle {
    private RegisterContract.Presenter mListener;

    public RegisterItemActionHandle(RegisterContract.Presenter listener) {
        mListener = listener;
    }

    public void registerUser() {
        if (mListener == null) return;
        mListener.registerUser();
    }

    public void openGallery() {
        if (mListener == null) return;
        mListener.openGallery();
    }

    public void changeScreenToLogin() {
        if (mListener == null) return;
        mListener.changeScreenToLogin();
    }

    public void changeScreenToForgotPassword() {
        if (mListener == null) return;
        mListener.changeScreenToForgotPassword();
    }
}
