package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.util.UserValidation;

/**
 * Created by tuanbg on 2/17/17.
 */
public interface RegisterContract {
    interface View extends BaseView {
        void chooseImage();
        void onValidateError(UserValidation.Error error);
    }

    interface Presenter {
        void registerUser();
        void openGallery();
        void setUserUrlImage(String url);
    }
}
