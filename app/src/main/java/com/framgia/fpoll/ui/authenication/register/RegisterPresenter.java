package com.framgia.fpoll.ui.authenication.register;

import android.util.Log;

import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/17/17.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private RegisterContract.View mView;
    private User mUser;
    private String mUrlAvatar;

    public RegisterPresenter(RegisterContract.View view, User user) {
        mView = view;
        mUser = user;
    }

    @Override
    public void registerUser() {
        Log.d(TAG, "registerUser: " + mUser.toString());
        Log.d(TAG, "registerUser: " + mUrlAvatar);
        new UserValidation(mUser).validate(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                Log.d(TAG, "onError: " + error);
                switch (error) {
                    case USER_NAME:
                        break;
                    case EMAL:
                        break;
                    case GENDER:
                        break;
                    case PASSWORD:
                        break;
                    case CONFIRM_PASSWORD:
                        break;
                    case AVATAR:
                        break;
                    default:
                        break;
                }
                mView.onValidateError(error);
            }

            @Override
            public void onValidateSuccess() {
                // TODO: 19/02/2017 Register
                Log.d(TAG, "onValidateSuccess: ");
            }
        });
    }

    @Override
    public void openGallery() {
        mView.chooseImage();
    }

    @Override
    public void setUserUrlImage(String url) {
        mUser.setAvatar(url);
        mUrlAvatar = url;
        Log.d(TAG, "setUserUrlImage: " + mUser.toString());
    }

    public User getUser() {
        return mUser;
    }
}
