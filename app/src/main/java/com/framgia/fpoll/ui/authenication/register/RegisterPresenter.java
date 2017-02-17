package com.framgia.fpoll.ui.authenication.register;

import com.framgia.fpoll.data.model.User;

/**
 * Created by tuanbg on 2/17/17.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
    }

    @Override
    public void registerUser() {
        User user = mView.registerAccount();
        if (mView.validateForm()) {
            // TODO: 2/17/17  push values in server
        }
    }

    @Override
    public void openGallery() {
        mView.chooseImage();
    }
}
