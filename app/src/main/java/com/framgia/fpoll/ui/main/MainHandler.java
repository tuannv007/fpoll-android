package com.framgia.fpoll.ui.main;

import android.support.annotation.NonNull;

/**
 * Created by Nhahv0902 on 3/16/2017.
 * <></>
 */
public class MainHandler {
    private MainContract.Presenter mListener;

    public MainHandler(@NonNull MainContract.Presenter listener) {
        mListener = listener;
    }

    public void clickUpdateProfile() {
        if (mListener != null) mListener.updateProfile();
    }

    public void clickStartUiPollCreation() {
        if (mListener != null) mListener.startUIPollCreation();
    }
}
