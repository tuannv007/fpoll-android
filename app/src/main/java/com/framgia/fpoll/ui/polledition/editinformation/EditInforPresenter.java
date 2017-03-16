package com.framgia.fpoll.ui.polledition.editinformation;

import android.text.TextUtils;

import com.framgia.fpoll.data.model.PollInformation;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforPresenter implements EditInforContract.Presenter {
    private EditInforContract.View mView;
    private PollInformation mPollInformation;

    public EditInforPresenter(EditInforContract.View view, PollInformation pollInformation) {
        mView = view;
        mPollInformation = pollInformation;
    }

    @Override
    public void nextStep() {
        mView.bindError();
        if (mView == null ||
            TextUtils.isEmpty(mPollInformation.getUserName()) ||
            TextUtils.isEmpty(mPollInformation.getEmail()) ||
            TextUtils.isEmpty(mPollInformation.getPollTitle()) ||
            (!android.util.Patterns.EMAIL_ADDRESS.matcher(mPollInformation.getEmail()).matches()))
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
