package com.framgia.fpoll.ui.login;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mView;
    private FpollGoogleApiClient mFpollGoogleApiClient;

    public LoginPresenter(LoginContract.View view, FpollGoogleApiClient FpollGoogleApiClient) {
        mView = view;
        mFpollGoogleApiClient = FpollGoogleApiClient;
        mView.start();
    }

    @Override
    public void initGoogle() {
        mFpollGoogleApiClient.initGoogle();
    }

    @Override
    public void loginGoogle() {
        mView.loginGoogle(mFpollGoogleApiClient.getGoogleApiClient());
    }

    @Override
    public void checkLoginGoogleResult(GoogleSignInResult result) {
        if (result.isSuccess() && result.getSignInAccount() != null) {
            requestGoogleToken(result.getSignInAccount().getEmail());
        } else mView.loginGoogleError();
    }

    @Override
    public void requestGoogleToken(String email) {
        mFpollGoogleApiClient.requestToken(email, new FpollGoogleApiClient.CallBack() {
                @Override
                public void onGetTokenSuccess(String token) {
                    mView.loginGoogleSuccess();
                    // TODO: 2/20/2017 send token to server and login
                }

                @Override
                public void onGetTokenFail() {
                    mView.loginGoogleError();
                }
            }
        );
    }
}
