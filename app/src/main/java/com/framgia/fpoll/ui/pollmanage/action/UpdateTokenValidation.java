package com.framgia.fpoll.ui.pollmanage.action;

import android.support.annotation.NonNull;

/**
 * Created by Nhahv0902 on 3/21/2017.
 * <></>
 */
public class UpdateTokenValidation {
    private String mLinkUser;
    private String mLinkAdmin;

    public UpdateTokenValidation(String linkUser, String linkAdmin) {
        mLinkUser = linkUser;
        mLinkAdmin = linkAdmin;
    }

    public void validate(@NonNull UpdateTokenCallback callback) {
        if (!isNonNull(mLinkUser)) {
            callback.onError(UpdateTokenError.LINK_USER);
            return;
        }
        if (!isNonNull(mLinkAdmin)) {
            callback.onError(UpdateTokenError.LINK_ADMIN);
            return;
        }
        callback.onSuccess();
    }

    private boolean isNonNull(String token) {
        return token != null && !token.trim().isEmpty();
    }

    public enum UpdateTokenError {
        LINK_USER, LINK_ADMIN
    }

    public interface UpdateTokenCallback {
        void onSuccess();

        void onError(UpdateTokenError error);
    }
}
