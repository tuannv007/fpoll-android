package com.framgia.fpoll.ui.login;

import com.framgia.fpoll.data.enums.LoginType;

/**
 * Created by Nhahv0902 on 2/20/2017.
 * <></>
 */
public class LoginActionHandler {
    private LoginPresenter mListener;

    public LoginActionHandler(LoginPresenter presenter) {
        mListener = presenter;
    }

    public void clickLogin(LoginType type) {
        if (mListener == null) return;
        switch (type) {
            case NORMAL:
                // TODO: 2/20/2017  handle call login normal
                break;
            case FACEBOOK:
                mListener.loginFacebook();
                break;
            case GOOGLE:
                mListener.loginGoogle();
                break;
            case TWITTER:
                // TODO: 2/20/2017  handle call login twitter
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
