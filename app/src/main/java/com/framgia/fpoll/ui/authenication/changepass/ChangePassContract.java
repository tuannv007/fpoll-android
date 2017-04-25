package com.framgia.fpoll.ui.authenication.changepass;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 4/24/17.
 */

public interface ChangePassContract {
    interface View extends BaseView {
        void dismissDialog();

        void changePassword();
    }

    interface Presenter {
        void changePassword();
    }
}
