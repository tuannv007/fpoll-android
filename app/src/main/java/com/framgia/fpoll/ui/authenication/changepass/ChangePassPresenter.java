package com.framgia.fpoll.ui.authenication.changepass;

/**
 * Created by tuanbg on 4/24/17.
 */

public class ChangePassPresenter implements ChangePassContract.Presenter {
    private ChangePassContract.View mView;

    public ChangePassPresenter(ChangePassContract.View view) {
        mView = view;
    }

    @Override
    public void changePassword() {
        // TODO: 4/25/17 change password to sever
    }
}
