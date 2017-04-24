package com.framgia.fpoll.data.source.remote.login;

import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.authorization.LoginNormalData;
import com.framgia.fpoll.data.model.authorization.SocialData;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public interface LoginDataSource {
    void loginSocial(@NonNull String token, String secret, @NonNull String provider,
            @NonNull DataCallback<SocialData> callback);

    void loginNormal(String email, String password,
            @NonNull DataCallback<LoginNormalData> callback);

    void logout(String header, @NonNull DataCallback<String> callback);

    void updateProfile(@NonNull User user, @NonNull DataCallback<SocialData> callback);

    void resetPassword(@NonNull String email, @NonNull DataCallback<String> callback);

    void getProfile(@NonNull String token, @NonNull DataCallback callback);
}
