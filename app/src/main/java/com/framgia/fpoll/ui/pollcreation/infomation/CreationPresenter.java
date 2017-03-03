package com.framgia.fpoll.ui.pollcreation.infomation;

import android.text.TextUtils;

import com.framgia.fpoll.data.model.PollInformation;

/**
 * Created by framgia on 20/02/2017.
 */
public class CreationPresenter implements CreationContract.Presenter {
    private CreationContract.View mView;
    private PollInformation mPollInformation;

    public CreationPresenter(CreationContract.View view, PollInformation pollInformation) {
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
}
