package com.framgia.fpoll.ui.pollmanage.information;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class PollInformationPresenter implements PollInformationContract.Presenter {
    private final PollInformationContract.View mView;

    public PollInformationPresenter(PollInformationContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void clickLinkVote() {
        mView.startUiVoting();
    }

    @Override
    public void clickViewOption() {
        mView.viewOption();
    }

    @Override
    public void clickViewSetting() {
        // TODO: 2/24/2017 handler click view setting
    }
}
