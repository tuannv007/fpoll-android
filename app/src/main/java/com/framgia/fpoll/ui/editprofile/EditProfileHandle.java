package com.framgia.fpoll.ui.editprofile;

/**
 * Created by tran.trung.phong on 21/02/2017.
 */
public class EditProfileHandle {
    private EditProfileContract.Presenter mPresenterListenner;

    public EditProfileHandle(EditProfileContract.Presenter listenner) {
        mPresenterListenner = listenner;
    }

    public void openGallery() {
        if (mPresenterListenner == null) return;
        mPresenterListenner.openGallery();
    }

    public void submitEditProfile() {
        if (mPresenterListenner == null) return;
        mPresenterListenner.submitEditProfile();
    }
}
