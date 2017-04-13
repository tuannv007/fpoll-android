package com.framgia.fpoll.data.source.remote.register;

import android.content.Context;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.AuthenticationApi;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by tuanbg on 2/28/17.
 */
public class RegisterRemoteDataSource implements RegisterDataSource {
    private static RegisterRemoteDataSource sRegisterRemoteDataSource;
    private static Context mContext;

    public static RegisterRemoteDataSource getInstance(Context context) {
        mContext = context;
        if (sRegisterRemoteDataSource == null) {
            sRegisterRemoteDataSource = new RegisterRemoteDataSource();
        }
        return sRegisterRemoteDataSource;
    }

    @Override
    public void register(User user, final RegisterCallBack callback) {
        if (callback == null) return;
        RequestBody email = RequestBody.create(MultipartBody.FORM, user.getEmail());
        RequestBody name = RequestBody.create(MultipartBody.FORM, user.getUsername());
        RequestBody password = RequestBody.create(MultipartBody.FORM, user.getPassword());
        RequestBody passwordConfirmation =
                RequestBody.create(MultipartBody.FORM, user.getConfirmPassword());
        RequestBody gender =
                RequestBody.create(MultipartBody.FORM, String.valueOf(user.getGender()));
        MultipartBody.Part avatar = AuthenticationApi.getAvatar(user);
        AuthenticationApi.RegisterService loginService =
                ServiceGenerator.createService(AuthenticationApi.RegisterService.class);
        loginService.registerUser(email, name, password, passwordConfirmation, gender, avatar)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<User>>() {
                            @Override
                            public void onResponse(ResponseItem<User> data) {
                                User user = data.getData();
                                callback.onSuccess(user);
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }
}
