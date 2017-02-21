package com.framgia.fpoll.ui.profileedit;

import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.util.UserValidation;

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
