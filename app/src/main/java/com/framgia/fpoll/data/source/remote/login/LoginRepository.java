package com.framgia.fpoll.data.source.remote.login;

import android.content.Context;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.LoginNormalData;
import com.framgia.fpoll.data.model.SocialData;
import com.framgia.fpoll.data.source.DataCallback;

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
    public void loginSocial(String provider, String token,
                            @NonNull final DataCallback<SocialData> callback) {
        mDataSource.loginSocial(provider, token, new DataCallback<SocialData>() {
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
    public void loginNormal(String email, String password,
                            @NonNull final DataCallback<LoginNormalData> callback) {
        mDataSource.loginNormal(email, password, new DataCallback<LoginNormalData>() {
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

    @Override
    public void logout(String header, @NonNull final DataCallback<String> callback) {
        mDataSource.logout(header, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
