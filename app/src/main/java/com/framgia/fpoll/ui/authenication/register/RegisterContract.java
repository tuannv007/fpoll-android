package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.data.model.User;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/17/17.
 */
public interface RegisterContract {
    interface View extends BaseView {
        boolean validateForm();
        User registerAccount();
        void chooseImage();
    }

    interface Presenter {
        void registerUser();
        void openGallery();
    }
}
