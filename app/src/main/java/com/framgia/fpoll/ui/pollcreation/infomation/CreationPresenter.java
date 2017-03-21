package com.framgia.fpoll.ui.pollcreation.infomation;

import android.text.TextUtils;

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
    }

    @Override
    public void nextStep() {
        mView.bindError();
        if (mView == null ||
            TextUtils.isEmpty(mPoll.getName()) ||
            TextUtils.isEmpty(mPoll.getEmail()) ||
            TextUtils.isEmpty(mPoll.getTitle()) ||
            (!android.util.Patterns.EMAIL_ADDRESS.matcher(mPoll.getEmail()).matches()))
            return;
        mView.nextStep();
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }

    @Override
    public void showTimePicker() {
        if (mView != null) mView.showTimePicker();
    }
}
