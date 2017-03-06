package com.framgia.fpoll.data.source.remote.login;

import android.content.Context;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.LoginNormalData;
import com.framgia.fpoll.data.model.SocialData;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public class LoginRepository implements LoginDataSource {
    private static LoginRepository sRepository;
    private LoginDataSource mDataSource;

    private LoginRepository(LoginDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static LoginRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new LoginRepository(LoginRemoteDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void loginSocial(String provider, String token, @NonNull final Callback callback) {
        mDataSource.loginSocial(provider, token, new Callback<SocialData>() {
            @Override
            public void onSuccess(SocialData data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void loginNormal(String email, String password, @NonNull final Callback callback) {
        mDataSource.loginNormal(email, password, new Callback<LoginNormalData>() {
            @Override
            public void onSuccess(LoginNormalData data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
