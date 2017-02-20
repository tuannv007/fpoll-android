package com.framgia.fpoll.ui.pollcreation;

/**
 * Created by framgia on 20/02/2017.
 */
public class CreatePollActionHandle {
    CreationContract.Presenter mListener;

    public CreatePollActionHandle(CreationContract.Presenter listener) {
        mListener = listener;
    }

    public void showDatePicker() {
        if (mListener != null) mListener.showDatePicker();
    }

    public void nextStep() {
        if (mListener != null) mListener.nextStep();
    }
}
