package com.framgia.fpoll.ui.pollcreation.infomation;

import com.framgia.fpoll.data.model.PollItem;

/**
 * Created by framgia on 20/02/2017.
 */
public class CreationPresenter implements CreationContract.Presenter {
    private CreationContract.View mView;
    private PollItem mPoll;

    public CreationPresenter(CreationContract.View view, PollItem poll) {
        mView = view;
        mPoll = poll;
        mView.start();
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }
}
