package com.framgia.fpoll.ui.main;

import android.support.v4.app.Fragment;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public interface MainContract {
    interface View extends BaseView {
        void showHelp();
        void addFragment(Fragment fragment, int title);
        void showMessage(String data);
        void startUiProfileEdition();
        void showProgressDialog();
        void hideProgressDialog();
    }

    interface Presenter {
        void logout();
        void updateProfile();
        void setInformation();
    }
}
