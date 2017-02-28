package com.framgia.fpoll.ui.editprofile;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tran.trung.phong on 20/02/2017.
 */
public class EditProfilePresenter implements EditProfileContract.Presenter {
    private String TAG = "EditProfilePresenter";
    private EditProfileContract.View mEditProfileView;
    private User mUser;

    public EditProfilePresenter(EditProfileContract.View view, User user) {
        mEditProfileView = view;
        mUser = user;
    }

    @Override
    public void submitEditProfile() {
        new UserValidation(mUser).validate(new UserValidation.CallBack() {
            @Override
            public void onError(UserValidation.Error error) {
                switch (error) {
                    case USER_NAME:
                        mEditProfileView.showMessageError(R.string.msg_username_not_empty);
                        break;
                    case EMAIL:
                        mEditProfileView.showMessageError(R.string.msg_email_invalidate);
                        break;
                    case PASSWORD:
                        mEditProfileView.showMessageError(R.string.msg_password_not_empty);
                        break;
                    case CONFIRM_PASSWORD:
                        mEditProfileView.showMessageError(R.string.msg_confirm_pass_not_success);
                        break;
                    case PASSWORD_LENGTH:
                        mEditProfileView.showMessageError(R.string.msg_length_password);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onValidateSuccess() {
                // show noti edit success
            }
        });
    }

    @Override
    public void openGallery() {
        mEditProfileView.chooseImage();
    }

    @Override
    public void setUserUrlImage(String url) {
        mUser.setAvatar(url);
    }

    public User getUser() {
        return mUser;
    }
}
