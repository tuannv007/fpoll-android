package com.framgia.fpoll.util;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.authorization.User;

/**
 * Created by framgia on 19/02/2017.
 */
public class UserValidation {
    private User mUser;

    public UserValidation(User user) {
        mUser = user;
    }

    public void validateEmailPassword(@NonNull CallBack callBack) {
        if (!isValidateEmail()) {
            callBack.onError(Error.EMAIL);
            return;
        }
        if (!isValidatePassword()) {
            callBack.onError(Error.PASSWORD);
            return;
        }
        callBack.onValidateSuccess();
    }

    public void validate(@NonNull CallBack callBack) {
        if (!isValidateUserName()) {
            callBack.onError(Error.USER_NAME);
            return;
        }
        if (!isValidateEmail()) {
            callBack.onError(Error.EMAIL);
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
        if (!isValidateLengthPassword()) {
            callBack.onError(Error.PASSWORD_LENGTH);
            return;
        }
        callBack.onValidateSuccess();
    }

    public void updateProfileValidate(@NonNull CallBack callBack) {
        if (!isValidateUserName()) {
            callBack.onError(Error.USER_NAME);
            return;
        }
        if (!isValidateEmail()) {
            callBack.onError(Error.EMAIL);
            return;
        }
        if (!isValidatePasswordUpdateProfile()) {
            callBack.onError(Error.PASSWORD);
            return;
        }
        callBack.onValidateSuccess();
    }

    private boolean isValidatePasswordUpdateProfile() {
        if ((mUser.getPassword() == null || mUser.getPassword().trim().isEmpty()) &&
            (mUser.getConfirmPassword() == null || mUser.getConfirmPassword().trim().isEmpty())) {
            return true;
        }
        return isValidatePassword() && isValidateConfirmPassword() && isValidateLengthPassword();
    }

    public void validateResetPass(@NonNull CallBack callBack) {
        if (!isValidateEmail()) {
            callBack.onError(Error.EMAIL);
            return;
        }
        callBack.onValidateSuccess();
    }

    public boolean isValidateConfirmPassword() {
        return mUser.getConfirmPassword() != null &&
            mUser.getConfirmPassword().equals(mUser.getPassword());
    }

    private boolean isValidateUserName() {
        return mUser.getUsername() != null && !mUser.getUsername().trim().isEmpty();
    }

    private boolean isValidateEmail() {
        return mUser.getEmail() != null && android.util.Patterns.EMAIL_ADDRESS.matcher(mUser
            .getEmail()).matches();
    }

    private boolean isValidatePassword() {
        return mUser.getPassword() != null && !mUser.getPassword().trim().isEmpty();
    }

    private boolean isValidateLengthPassword() {
        return mUser.getPassword() != null &&
            mUser.getPassword().trim().length() >= Constant.MIN_LENGTH_PASSWORD;
    }

    public enum Error {
        USER_NAME,
        EMAIL,
        GENDER,
        PASSWORD,
        CONFIRM_PASSWORD,
        AVATAR,
        PASSWORD_LENGTH,
    }

    public interface CallBack {
        void onError(Error error);
        void onValidateSuccess();
    }
}
