package com.framgia.fpoll.ui.main;

import com.framgia.fpoll.data.model.User;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;
    private User mUser = new User();

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.start();
    }

    public User getUser() {
        return mUser;
    }
}
