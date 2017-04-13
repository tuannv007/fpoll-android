package com.framgia.fpoll.ui.authenication.login;

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

        void showMessageError(int msg);

        void showProgressDialog();

        void hideProgressDialog();

        FPollGoogleApiClient newGoogleClient();

        FPollTwitterAuthClient newTwitterClient();
    }

    interface Presenter {
        void initFacebook();

        void loginGoogle();

        void loginFacebook();

        void requestGoogleToken(String email);

        void switchForgotPassword();

        void switchUiRegister();

        void checkLoginGoogleResult(GoogleSignInResult result);

        boolean checkLoginFacebook(int requestCode, int resultCode, Intent data);

        void loginTwitter();

        void checkLoginTwitter(int requestCode, int resultCode, Intent data);

        void loginAccount();
    }
}
