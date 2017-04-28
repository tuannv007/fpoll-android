package com.framgia.fpoll.data.source.remote.login;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.authorization.LoginNormalData;
import com.framgia.fpoll.data.model.authorization.SocialData;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.ResponseItem;
import rx.Observable;

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
    public void loginSocial(@NonNull String token, String secret, @NonNull String provider,
            @NonNull final DataCallback<SocialData> callback) {
        mDataSource.loginSocial(token, secret, provider, new DataCallback<SocialData>() {
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

    @Override
    public void updateProfile(@NonNull User user,
            @NonNull final DataCallback<SocialData> callback) {
        if (mDataSource == null) return;
        mDataSource.updateProfile(user, new DataCallback<SocialData>() {
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
    public void resetPassword(@NonNull String email, @NonNull final DataCallback<String> callback) {
        if (mDataSource == null) return;
        mDataSource.resetPassword(email, new DataCallback<String>() {
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

    @Override
    public void getProfile(@NonNull String token, @NonNull final DataCallback callback) {
        mDataSource.getProfile(token, new DataCallback<ResponseItem<User>>() {
            @Override
            public void onSuccess(ResponseItem<User> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public Observable<Boolean> changePassword(@NonNull String currentPassword, @NonNull String newPassword,
            @NonNull String newPasswordConfirmation) {
        return mDataSource.changePassword(currentPassword, newPassword, newPasswordConfirmation);
    }
}
