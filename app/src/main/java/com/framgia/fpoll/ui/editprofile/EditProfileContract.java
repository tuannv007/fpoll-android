package com.framgia.fpoll.ui.editprofile;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tran.trung.phong on 20/02/2017.
 */
public interface EditProfileContract {
    interface View extends BaseView {
        void chooseImage();
        void showMessageError(int message);
    }

    interface Presenter {
        void submitEditProfile();
        void openGallery();
        void setUserUrlImage(String url);
    }
}
