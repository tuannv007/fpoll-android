package com.framgia.fpoll.ui.authenication.changepass;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 4/24/17.
 */

public interface ChangePassContract {
    interface View extends BaseView {
        void dismissDialog();

        void changePassword();

        void showProgressDialog();

        void hideProgressDialog();

        void onChangePasswordError(String message);

        void onChangePasswordSuccess();

        void onCurrentPasswordEmpty();

        void onNewPasswordEmpty();

        void onNewPasswordConfirmationEmpty();

        void onNewPassNotSame();

        void onDestroy();

        void dismissView();
    }

    interface Presenter {
        void changePassword(String currentPassword, String newPassword,
                String newPasswordConfirmation);

        boolean validateData(String currentPassword, String newPassword,
                String newPasswordConfirmation);

        void onDestroy();
    }
}
