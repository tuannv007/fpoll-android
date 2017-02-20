package com.framgia.fpoll.ui.authenication.activity;

/**
 * Created by Nhahv0902 on 2/20/2017.
 * <></>
 */
public class AuthenticationPresenter implements AuthenticationContract.Presenter {
    private final AuthenticationContract.View mView;

    public AuthenticationPresenter(AuthenticationContract.View view) {
        mView = view;
        mView.start();
    }
}
