package com.framgia.fpoll.ui.pollmanage.information;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class InformationPresenter implements InformationContract.Presenter {
    private final InformationContract.View mView;

    public InformationPresenter(InformationContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void clickLinkVote() {
        mView.startUiVoting();
    }

    @Override
    public void clickViewOption() {
        // TODO: 2/24/2017 handler click view optional
    }

    @Override
    public void clickViewSetting() {
        // TODO: 2/24/2017 handler click view setting
    }
}
