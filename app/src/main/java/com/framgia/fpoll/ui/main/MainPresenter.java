package com.framgia.fpoll.ui.main;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.start();
    }


}
