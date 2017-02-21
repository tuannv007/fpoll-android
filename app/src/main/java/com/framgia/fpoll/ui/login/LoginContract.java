package com.framgia.fpoll.ui.login;

import android.content.Intent;

import com.framgia.fpoll.ui.base.BaseView;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public interface LoginContract {
    interface View extends BaseView {
        void loginGoogle(GoogleApiClient googleApiClient);
        void loginSuccess();
        void loginError();
        void loginFacebook();
        void changeUiForgotPassword();
        void changeUiRegister();
    }

    interface Presenter {
        void loginGoogle();
        void requestGoogleToken(String email);
        void initGoogle();
        void checkLoginGoogleResult(GoogleSignInResult result);
        void loginFacebook();
        void switchForgotPassword();
        void switchUiRegister();
        void initFacebook();
        void checkLoginFacebook(int requestCode, int resultCode, Intent data);
    }
}
