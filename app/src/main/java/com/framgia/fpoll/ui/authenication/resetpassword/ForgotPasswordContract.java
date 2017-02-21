package com.framgia.fpoll.ui.authenication.resetpassword;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/21/17.
 */
public interface ForgotPasswordContract {
    interface View extends BaseView {
        void showMessageError();
    }

    interface Presenter {
        void resetPassword();
    }
}
