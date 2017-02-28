package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/17/17.
 */
public interface RegisterContract {
    interface View extends BaseView {
        void chooseImage();
        void showMessageError(int message);
        void switchUiLogin();
        void switchUiForgotPassword();
        void registerSuccess(User user);
        void showDialog();
        void dismissDialog();
        void showRegisterError(String message);
    }

    interface Presenter {
        void registerUser();
        void openGallery();
        void setUserUrlImage(String url);
        void switchUiLogin();
        void switchUiForgotPassword();
    }
}
