package com.framgia.fpoll.ui.pollmanage;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class ManagePollPresenter implements ManagePollContract.Presenter {
    private final ManagePollContract.View mView;

    public ManagePollPresenter(ManagePollContract.View view) {
        mView = view;
        mView.start();
    }
}
