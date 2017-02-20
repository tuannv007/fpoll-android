package com.framgia.fpoll.util;

import android.text.TextUtils;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.User;

/**
 * Created by framgia on 19/02/2017.
 */
public class UserValidation {
    public enum Error {
        USER_NAME,
        EMAL,
        GENDER,
        PASSWORD,
        CONFIRM_PASSWORD,
        AVATAR
    }

    public interface CallBack {
        void onError(Error error);
        void onValidateSuccess();
    }

    private User mUser;

    public UserValidation(User user) {
        mUser = user;
    }

    public void validate(@NonNull CallBack callBack) {
        if (!isValidateUserName()) {
            callBack.onError(Error.USER_NAME);
            return;
        }
        if (!isValidateEmail()) {
            callBack.onError(Error.EMAL);
            return;
        }
        if (!isValidateGender()) {
            callBack.onError(Error.GENDER);
            return;
        }
        if (!isValidatePassword()) {
            callBack.onError(Error.PASSWORD);
            return;
        }
        if (!isValidateConfirmPassword()) {
            callBack.onError(Error.CONFIRM_PASSWORD);
            return;
        }
        if (!isValidatAvatar()) {
            callBack.onError(Error.AVATAR);
            return;
        }
        callBack.onValidateSuccess();
    }

    public boolean isValidateUserName() {
        return !TextUtils.isEmpty(mUser.getUsername());
    }

    public boolean isValidateEmail() {
        return false;
    }

    public boolean isValidateGender() {
        return true;
    }

    public boolean isValidatePassword() {
        return !TextUtils.isEmpty(mUser.getPassword());
    }

    public boolean isValidateConfirmPassword() {
        return !TextUtils.isEmpty(mUser.getUsername())
            && mUser.getPassword().equals(mUser.getConfirmPassword());
    }

    public boolean isValidatAvatar() {
        return !TextUtils.isEmpty(mUser.getAvatar());
    }
    ///....
}
