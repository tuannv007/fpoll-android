package com.framgia.fpoll.ui.authenication.login;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.LoginNormalData;
import com.framgia.fpoll.data.model.authorization.SocialData;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.util.UserValidation;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;

import static com.framgia.fpoll.util.Constant.PreferenceConstant.PREF_EMAIL;
import static com.framgia.fpoll.util.Constant.PreferenceConstant.PREF_PASSWORD;
import static com.framgia.fpoll.util.Constant.PreferenceConstant.PREF_REMEM_BER;

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
    private SharePreferenceUtil mPreference;
    private LoginRepository mRepository;
    private ObservableBoolean mIsRemember = new ObservableBoolean();

    public LoginPresenter(LoginContract.View view, LoginRepository repository,
            SharePreferenceUtil preference) {
        mView = view;
        mRepository = repository;
        mPreference = preference;
        mUser = new User();
        mView.start();
        mUser.setEmail(mPreference.getString(PREF_EMAIL));
        mUser.setPassword(mPreference.getString(PREF_PASSWORD));
        mIsRemember.set(mPreference.getBoolean(PREF_REMEM_BER));
    }

    @Override
    public void initFacebook() {
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance()
                .registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        mRepository.loginSocial(loginResult.getAccessToken().getToken(), null,
                                LoginType.FACEBOOK.getProvider(), new DataCallback<SocialData>() {
                                    @Override
                                    public void onSuccess(SocialData data) {
                                        data.getUser().setToken(data.getToken());
                                        mPreference.writeUser(data.getUser());
                                        writeLogin();
                                        mView.loginSuccess();
                                    }

                                    @Override
                                    public void onError(String msg) {
                                        mView.loginError();
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
    public void loginGoogle() {
        if (mFPollGoogleApiClient == null) mFPollGoogleApiClient = mView.newGoogleClient();
        mView.loginGoogle(mFPollGoogleApiClient.getGoogleApiClient());
    }

    @Override
    public void checkLoginGoogleResult(GoogleSignInResult result) {
        if (result.isSuccess() && result.getSignInAccount() != null) {
            requestGoogleToken(result.getSignInAccount().getEmail());
        } else {
            mView.loginError();
        }
    }

    @Override
    public void loginFacebook() {
        mView.showProgressDialog();
        mView.loginFacebook();
    }

    @Override
    public void loginTwitter() {
        mView.showProgressDialog();
        if (mFPollTwitterAuthClient == null) mFPollTwitterAuthClient = mView.newTwitterClient();
        mFPollTwitterAuthClient.loginTwitter(new FPollTwitterAuthClient.Callback() {
            @Override
            public void loginTwitterSuccess(TwitterAuthToken token) {
                mRepository.loginSocial(token.token, token.secret, LoginType.TWITTER.getProvider(),
                        new DataCallback<SocialData>() {
                            @Override
                            public void onSuccess(SocialData data) {
                                data.getUser().setToken(data.getToken());
                                mPreference.writeUser(data.getUser());
                                writeLogin();
                                mView.loginSuccess();
                            }

                            @Override
                            public void onError(String msg) {
                                mView.loginError();
                            }
                        });
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
                mView.showProgressDialog();
                mRepository.loginNormal(mUser.getEmail(), mUser.getPassword(),
                        new DataCallback<LoginNormalData>() {
                            @Override
                            public void onSuccess(LoginNormalData data) {
                                saveEmailPassword();
                                data.getUser().setToken(data.getAccessToken());
                                mPreference.writeUser(data.getUser());
                                writeLogin();
                                mView.loginSuccess();
                            }

                            @Override
                            public void onError(String msg) {
                                mView.loginError();
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
        if (mFPollTwitterAuthClient == null) mFPollTwitterAuthClient = mView.newTwitterClient();
        mFPollTwitterAuthClient.getAuthClient().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void requestGoogleToken(String email) {
        if (mFPollGoogleApiClient == null) mFPollGoogleApiClient = mView.newGoogleClient();
        mFPollGoogleApiClient.requestToken(email, new FPollGoogleApiClient.CallBack() {
            @Override
            public void onGetTokenSuccess(String token) {
                mRepository.loginSocial(token, null, LoginType.GOOGLE.getProvider(),
                        new DataCallback<SocialData>() {
                            @Override
                            public void onSuccess(SocialData data) {
                                data.getUser().setToken(data.getToken());
                                mPreference.writeUser(data.getUser());
                                writeLogin();
                                mView.loginSuccess();
                            }

                            @Override
                            public void onError(String msg) {
                                mView.loginError();
                            }
                        });
            }

            @Override
            public void onGetTokenFail() {
                mView.loginError();
            }
        });
    }

    private void saveEmailPassword() {
        mPreference.writePreference(PREF_REMEM_BER, mIsRemember.get());
        if (mIsRemember.get()) {
            mPreference.writePreference(PREF_EMAIL, mUser.getEmail());
            mPreference.writePreference(PREF_PASSWORD, mUser.getPassword());
        } else {
            mPreference.clearKey(PREF_EMAIL);
            mPreference.clearKey(PREF_PASSWORD);
        }
    }

    public void writeLogin() {
        mPreference.writeLogin(true);
    }

    public ObservableBoolean getIsRemember() {
        return mIsRemember;
    }

    public User getUser() {
        return mUser;
    }
}
