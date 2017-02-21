package com.framgia.fpoll.ui.login;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mView;
    private FPollGoogleApiClient mFPollGoogleApiClient;
    private CallbackManager mCallbackManager;

    public LoginPresenter(LoginContract.View view, FPollGoogleApiClient FpollGoogleApiClient) {
        mView = view;
        mFPollGoogleApiClient = FpollGoogleApiClient;
        mView.start();
    }

    @Override
    public void initGoogle() {
        mFPollGoogleApiClient.initGoogle();
    }

    @Override
    public void initFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(final LoginResult loginResult) {
                    // TODO: 2/21/2017 login facebook success
                }

                @Override
                public void onCancel() {
                    mView.loginError();
                }

                @Override
                public void onError(FacebookException exception) {
                    mView.loginError();
                }
            });
    }

    @Override
    public void loginGoogle() {
        mView.loginGoogle(mFPollGoogleApiClient.getGoogleApiClient());
    }

    @Override
    public void checkLoginGoogleResult(GoogleSignInResult result) {
        if (result.isSuccess() && result.getSignInAccount() != null) {
            requestGoogleToken(result.getSignInAccount().getEmail());
        } else mView.loginError();
    }

    @Override
    public void loginFacebook() {
        mView.loginFacebook();
    }

    @Override
    public void switchForgotPassword() {
        mView.changeUiForgotPassword();
    }

    @Override
    public void switchUiRegister() {
        mView.changeUiRegister();
    }

    @Override
    public void checkLoginFacebook(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestGoogleToken(String email) {
        mFPollGoogleApiClient.requestToken(email, new FPollGoogleApiClient.CallBack() {
                @Override
                public void onGetTokenSuccess(String token) {
                    mView.loginSuccess();
                }

                @Override
                public void onGetTokenFail() {
                    mView.loginError();
                }
            }
        );
    }
}
