package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.remote.register.RegisterDataSource;
import com.framgia.fpoll.data.source.remote.register.RegisterRepository;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/17/17.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private RegisterContract.View mView;
    private User mUser;
    private RegisterRepository mRepository;

    public RegisterPresenter(RegisterContract.View view, User user,
                             RegisterRepository registerRepository) {
        mRepository = registerRepository;
        mView = view;
        mUser = user;
    }

    @Override
    public void registerUser() {
        new UserValidation(mUser).validate(new UserValidation.CallBack() {
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
                        mView.showMessageError(R.string.msg_password_not_empty);
                        break;
                    case CONFIRM_PASSWORD:
                        mView.showMessageError(R.string.msg_confirm_pass_not_success);
                        break;
                    case PASSWORD_LENGTH:
                        mView.showMessageError(R.string.msg_length_password);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onValidateSuccess() {
                mView.showDialog();
                mRepository.register(mUser, new RegisterDataSource.RegisterCallBack() {
                    @Override
                    public void onSuccess(User user) {
                        mView.registerSuccess(user);
                    }

                    @Override
                    public void onError(String message) {
                        mView.showRegisterError(message);
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
    public void switchUiLogin() {
        mView.switchUiLogin();
    }

    @Override
    public void switchUiForgotPassword() {
        mView.switchUiForgotPassword();
    }

    @Override
    public void setUserUrlImage(String url) {
        mUser.setAvatar(url);
    }

    public User getUser() {
        return mUser;
    }
}
