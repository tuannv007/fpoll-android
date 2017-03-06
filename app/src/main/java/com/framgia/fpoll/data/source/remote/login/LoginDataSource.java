package com.framgia.fpoll.data.source.remote.login;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.SocialData;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public interface LoginDataSource {
    interface Callback {
        void onSuccess(SocialData data);
        void onError(String msg);
    }
    void loginSocial(String provider, String token, @NonNull Callback callback);
}
