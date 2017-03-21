package com.framgia.fpoll.ui.authenication.resetpassword;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/21/17.
 */
public interface ForgotPasswordContract {
    interface View extends BaseView {
        void showMessageError();
        void showMessage(String message);
        void showDialog();
        void dismissDialog();
    }

    interface Presenter {
        void resetPassword();
    }
}
