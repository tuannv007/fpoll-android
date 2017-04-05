package com.framgia.fpoll.ui.polledition.editinformation;

import com.framgia.fpoll.data.model.PollItem;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforPresenter implements EditInforContract.Presenter {
    private EditInforContract.View mView;
    private PollItem mPollInformation;

    public EditInforPresenter(EditInforContract.View view, PollItem pollItem) {
        mView = view;
        mPollInformation = pollItem;
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
