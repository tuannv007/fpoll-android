package com.framgia.fpoll.ui.login;

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
        void loginGoogleSuccess();
        void loginGoogleError();
    }

    interface Presenter {
        void loginGoogle();
        void requestGoogleToken(String email);
        void initGoogle();
        void checkLoginGoogleResult(GoogleSignInResult result);
    }
}
