package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/17/17.
 */
public interface RegisterContract {
    interface View extends BaseView {
        void chooseImage();
        void showMessageError(int message);
    }

    interface Presenter {
        void registerUser();
        void openGallery();
        void setUserUrlImage(String url);
    }
}
