package com.framgia.fpoll.data.source.remote.resetpassword;

import android.content.Context;

import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.AuthenticationApi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by tuanbg on 3/14/17.
 */
public class ResetRemoteDataSource implements ResetDataSource {
    private static ResetRemoteDataSource sRemoteDataSource;
    private Context mContext;

    public ResetRemoteDataSource(Context context) {
        mContext = context;
    }

    public static ResetRemoteDataSource getInstance(Context context) {
        if (sRemoteDataSource == null) {
            sRemoteDataSource = new ResetRemoteDataSource(context);
        }
        return sRemoteDataSource;
    }

    @Override
    public void resetPassword(User user, final DataCallback callback) {
        if (callback == null) return;
        RequestBody email =
            RequestBody.create(MultipartBody.FORM, user.getEmail());
        AuthenticationApi.ResetPasswordService getData =
            ServiceGenerator.createService(AuthenticationApi.ResetPasswordService.class);
        getData.resetPassword(email).enqueue(new CallbackManager<>
            (mContext, new CallbackManager.CallBack<ResponseItem>() {
                @Override
                public void onResponse(ResponseItem data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
