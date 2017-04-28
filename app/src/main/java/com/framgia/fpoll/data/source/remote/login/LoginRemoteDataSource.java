package com.framgia.fpoll.data.source.remote.login;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.authorization.LoginNormalBody;
import com.framgia.fpoll.data.model.authorization.LoginNormalData;
import com.framgia.fpoll.data.model.authorization.SocialData;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.AuthenticationApi;
import com.framgia.fpoll.util.ActivityUtil;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;

import static com.framgia.fpoll.util.Constant.DataConstant.STATUS_SUCCESS;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public class LoginRemoteDataSource implements LoginDataSource {
    private static LoginRemoteDataSource sRemoteDataSource;
    private Context mContext;
    private AuthenticationApi.LoginService mService;

    private LoginRemoteDataSource(Context context) {
        mContext = context;
        mService = ServiceGenerator.createService(AuthenticationApi.LoginService.class);
    }

    public static LoginDataSource getInstance(Context context) {
        if (sRemoteDataSource == null) sRemoteDataSource = new LoginRemoteDataSource(context);
        return sRemoteDataSource;
    }

    @Override
    public void loginSocial(@NonNull String token, String secret, @NonNull String provider,
            @NonNull final DataCallback<SocialData> callback) {
        if (mService == null) return;
        mService.loginSocial(token, secret, provider)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<SocialData>>() {
                            @Override
                            public void onResponse(ResponseItem<SocialData> data) {
                                if (data != null && data.getData() != null) {
                                    callback.onSuccess(data.getData());
                                }
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
        if (mService == null) return;
        LoginNormalBody user = new LoginNormalBody(email, password);
        mService.loginNormal(user)
                .enqueue(new CallbackManager<>(mContext,
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
        if (mService == null) return;
        mService.logout(header)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void updateProfile(@NonNull User user,
            @NonNull final DataCallback<SocialData> callback) {
        if (mService == null) return;
        RequestBody email = RequestBody.create(MultipartBody.FORM, user.getEmail());
        RequestBody name = RequestBody.create(MultipartBody.FORM, user.getUsername());
        RequestBody password = null;
        if (user.getPassword() != null) {
            password = RequestBody.create(MultipartBody.FORM, user.getPassword());
        }
        RequestBody gender =
                RequestBody.create(MultipartBody.FORM, String.valueOf(user.getGender()));
        RequestBody chatWorkId = null;
        if (user.getChatWorkId() != null) {
            chatWorkId = RequestBody.create(MultipartBody.FORM, user.getChatWorkId());
        }
        MultipartBody.Part avatar = AuthenticationApi.getAvatar(user);
        mService.updateProfile(name, email, password, gender, chatWorkId, avatar)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<SocialData>>() {
                            @Override
                            public void onResponse(ResponseItem<SocialData> data) {
                                callback.onSuccess(data.getData());
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void resetPassword(@NonNull String email, @NonNull final DataCallback<String> callback) {
        if (mService == null) return;
        RequestBody bodyEmail = RequestBody.create(MultipartBody.FORM, email);
        mService.resetPassword(bodyEmail)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data.getMessage().toString());
                                } else {
                                    callback.onError(data.getMessage().toString());
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getProfile(@NonNull String token, @NonNull final DataCallback callback) {
        if (mService == null) return;
        mService.getProfile(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<User>>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data);
                                } else {
                                    callback.onError(data.getMessage().toString());
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public Observable<Boolean> changePassword(@NonNull String currentPassword,
            @NonNull String newPassword, @NonNull String newPasswordConfirmation) {
        return mService.changePassword(currentPassword, newPassword, newPasswordConfirmation)
                .flatMap(new Func1<ResponseItem, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(ResponseItem responseItem) {
                        if (responseItem == null) {
                            return Observable.error(new NullPointerException());
                        } else {
                            if (responseItem.isError()) {
                                return Observable.error(new NullPointerException(
                                        ActivityUtil.byString(responseItem.getMessage())));
                            } else {
                                return Observable.just(!responseItem.isError());
                            }
                        }
                    }
                });
    }
}
