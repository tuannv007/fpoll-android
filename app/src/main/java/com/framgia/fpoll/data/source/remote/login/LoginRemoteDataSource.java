package com.framgia.fpoll.data.source.remote.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;
import com.framgia.fpoll.data.ApiRestClient.APIService.authenticationservice.AuthenticationApi;
import com.framgia.fpoll.data.ApiRestClient.CallbackManager;
import com.framgia.fpoll.data.ApiRestClient.ServiceGenerator;
import com.framgia.fpoll.data.model.LoginNormalBody;
import com.framgia.fpoll.data.model.LoginNormalData;
import com.framgia.fpoll.data.model.SocialData;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public class LoginRemoteDataSource implements LoginDataSource {
    private static LoginRemoteDataSource sRemoteDataSource;
    private Context mContext;

    private LoginRemoteDataSource(Context context) {
        mContext = context;
    }

    public static LoginDataSource getInstance(Context context) {
        if (sRemoteDataSource == null) sRemoteDataSource = new LoginRemoteDataSource(context);
        return sRemoteDataSource;
    }

    @Override
    public void loginSocial(String provider, String token,
                            @NonNull final DataCallback<SocialData> callback) {
        ServiceGenerator.createService(AuthenticationApi.LoginService.class)
            .loginSocial(provider, token).enqueue(new CallbackManager<>(mContext,
            new CallbackManager.CallBack<ResponseItem<SocialData>>() {
                @Override
                public void onResponse(ResponseItem<SocialData> data) {
                    if (data != null) callback.onSuccess(data.getData());
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }

    @Override
    public void loginNormal(String email, String password,
                            @NonNull final DataCallback<LoginNormalData> callback) {
        LoginNormalBody user = new LoginNormalBody(email, password);
        ServiceGenerator.createService(AuthenticationApi.LoginService.class)
            .loginNormal(user).enqueue(new CallbackManager<>(mContext,
            new CallbackManager.CallBack<ResponseItem<LoginNormalData>>() {
                @Override
                public void onResponse(ResponseItem<LoginNormalData> data) {
                    callback.onSuccess(data.getData());
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }

    @Override
    public void logout(String header, @NonNull final DataCallback<String> callback) {
        ServiceGenerator.createService(AuthenticationApi.LoginService.class).logout(header).enqueue(
            new CallbackManager<>(mContext, new CallbackManager.CallBack<ResponseItem<Object>>() {
                @Override
                public void onResponse(ResponseItem<Object> data) {
                    callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
