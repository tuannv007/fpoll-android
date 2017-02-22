package com.framgia.fpoll.ui.polloption;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionHandler {
    private OptionPollContract.Presenter mListener;

    public OptionHandler(OptionPollContract.Presenter listener) {
        mListener = listener;
    }

    public void nextStep() {
        if (mListener != null) mListener.nextStep();
    }

    public void previousStep() {
        if (mListener != null) mListener.previousStep();
    }
}
