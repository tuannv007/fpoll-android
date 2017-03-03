package com.framgia.fpoll.ui.authenication.login;

/**
 * Created by Nhahv0902 on 2/20/2017.
 * <></>
 */
public class LoginActionHandler {
    private LoginContract.Presenter mListener;

    public LoginActionHandler(LoginContract.Presenter presenter) {
        mListener = presenter;
    }

    public void clickLogin(LoginType type) {
        if (mListener == null) return;
        switch (type) {
            case NORMAL:
                mListener.loginAccount();
                break;
            case FACEBOOK:
                mListener.loginFacebook();
                break;
            case GOOGLE:
                mListener.loginGoogle();
                break;
            case TWITTER:
                mListener.loginTwitter();
                break;
            default:
                break;
        }
    }

    public void clickForgotPassword() {
        mListener.switchForgotPassword();
    }

    public void clickRegister() {
        mListener.switchUiRegister();
    }
}
