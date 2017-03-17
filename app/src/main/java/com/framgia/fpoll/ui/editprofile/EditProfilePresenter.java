package com.framgia.fpoll.ui.editprofile;

import android.support.annotation.NonNull;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tran.trung.phong on 20/02/2017.
 */
public class EditProfilePresenter implements EditProfileContract.Presenter {
    private String TAG = "EditProfilePresenter";
    private EditProfileContract.View mView;
    private User mUser;
    private SharePreferenceUtil mPreference;
    private LoginRepository mRepository;

    public EditProfilePresenter(@NonNull EditProfileContract.View view,
                                @NonNull SharePreferenceUtil preference,
                                @NonNull LoginRepository repository) {
        mView = view;
        mPreference = preference;
        mRepository = repository;
        mUser = mPreference.getUser();
    }

    @Override
    public void submitEditProfile() {
        if (mRepository == null || mUser == null) return;
        new UserValidation(mUser).updateProfileValidate(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                switch (error) {
                    case USER_NAME:
                        mView.showMessageError(R.string.msg_username_not_empty);
                        break;
                    case EMAIL:
                        mView.showMessageError(R.string.msg_email_invalidate);
                        break;
                    case PASSWORD:
                        mView.showMessageError(R.string.msg_password_error);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onValidateSuccess() {
                mView.showProgressDialog();
                mRepository.updateProfile(mUser, new DataCallback<User>() {
                    @Override
                    public void onSuccess(User data) {
                        mPreference.writeUser(data);
                        mView.showMessageError(R.string.msg_update_profile_success);
                        mView.hideProgressDialog();
                    }

                    @Override
                    public void onError(String msg) {
                        mView.showMessage(msg);
                        mView.hideProgressDialog();
                    }
                });
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
    }

    public User getUser() {
        return mUser;
    }
}
