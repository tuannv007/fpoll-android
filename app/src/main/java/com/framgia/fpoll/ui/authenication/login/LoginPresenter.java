package com.framgia.fpoll.ui.authenication.login;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.LoginNormalData;
import com.framgia.fpoll.data.model.SocialData;
import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.util.UserValidation;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.twitter.sdk.android.core.TwitterException;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mView;
    private FPollGoogleApiClient mFPollGoogleApiClient;
    private FPollTwitterAuthClient mFPollTwitterAuthClient;
    private CallbackManager mCallbackManager;
    private User mUser;
    private LoginRepository mRepository;

    public LoginPresenter(LoginContract.View view, FPollGoogleApiClient FpollGoogleApiClient,
                          FPollTwitterAuthClient twitterAuthClient, LoginRepository repository) {
        mView = view;
        mFPollGoogleApiClient = FpollGoogleApiClient;
        mFPollTwitterAuthClient = twitterAuthClient;
        mRepository = repository;
        mUser = new User();
        mUser.setEmail("tuan.dev.ad@gmail.com");
        mUser.setPassword("12345678");
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
                    mRepository.loginSocial(LoginType.FACEBOOK.getProvider(),
                        loginResult.getAccessToken().getToken(), new DataCallback<SocialData>() {
                            @Override
                            public void onSuccess(SocialData data) {
                                // TODO: 3/3/2017  login facebook success
                            }

                            @Override
                            public void onError(String msg) {
                            }
                        });
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
    public void initTwitter() {
        mFPollTwitterAuthClient.initTwitter();
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
    public void loginTwitter() {
        mFPollTwitterAuthClient.loginTwitter(new FPollTwitterAuthClient.Callback() {
            @Override
            public void loginTwitterSuccess(String token) {
                mView.loginSuccess();
            }

            @Override
            public void loginTwitterError(TwitterException exception) {
                exception.printStackTrace();
                mView.loginError();
            }
        });
    }

    @Override
    public void loginAccount() {
        new UserValidation(mUser).validateEmailPassword(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                switch (error) {
                    case EMAIL:
                        mView.showMessageError(R.string.msg_email_invalidate);
                        break;
                    case PASSWORD:
                        mView.showMessageError(R.string.msg_password_not_empty);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onValidateSuccess() {
                mRepository.loginNormal(mUser.getEmail(), mUser.getPassword(),
                    new DataCallback<LoginNormalData>() {
                        @Override
                        public void onSuccess(LoginNormalData data) {
                            // TODO: 2/22/2017 handle login account success
                        }

                        @Override
                        public void onError(String msg) {
                        }
                    });
            }
        });
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
    public boolean checkLoginFacebook(int requestCode, int resultCode, Intent data) {
        return mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void checkLoginTwitter(int requestCode, int resultCode, Intent data) {
        mFPollTwitterAuthClient.getAuthClient().onActivityResult(requestCode, resultCode, data);
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

    public User getUser() {
        return mUser;
    }
}
