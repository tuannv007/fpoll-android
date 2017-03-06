package com.framgia.fpoll.data.source.remote.login;

import com.android.annotations.NonNull;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public interface LoginDataSource {
    interface Callback <T>{
        void onSuccess(T data);
        void onError(String msg);
    }
    void loginSocial(String provider, String token, @NonNull Callback callback);
    void loginNormal(String email, String password, @NonNull Callback callback);
}
