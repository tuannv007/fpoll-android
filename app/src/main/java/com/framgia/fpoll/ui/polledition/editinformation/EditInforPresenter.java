package com.framgia.fpoll.ui.polledition.editinformation;

import android.text.TextUtils;

import com.framgia.fpoll.data.model.PollItem;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforPresenter implements EditInforContract.Presenter {
    private EditInforContract.View mView;
    private PollItem mPoll;

    public EditInforPresenter(EditInforContract.View view, PollItem poll) {
        mView = view;
        mPoll = poll;
    }

    @Override
    public void nextStep() {
        mView.bindError();
        if (mView == null ||
            TextUtils.isEmpty(mPoll.getUser().getUsername()) ||
            TextUtils.isEmpty(mPoll.getUser().getEmail()) ||
            TextUtils.isEmpty(mPoll.getTitle()) ||
            (!android.util.Patterns.EMAIL_ADDRESS.matcher(mPoll.getUser().getEmail()).matches()))
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
