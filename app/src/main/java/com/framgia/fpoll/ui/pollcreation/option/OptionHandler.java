package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.data.model.OptionItem;

/**
 * Created by framgia on 22/02/2017.
 * <></>
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

    public void clickPickImage(OptionItem optionItem, int position) {
        if (mListener != null) mListener.pickImage(optionItem, position);
    }

    public void clickPickDate(OptionItem optionItem, int position) {
        if (mListener != null) mListener.pickDate(optionItem, position);
    }

    public void clickDeletePoll(OptionItem optionItem, int position) {
        if (mListener != null) mListener.deletePoll(optionItem, position);
    }

    public void clickAugmentPoll() {
        if (mListener != null) mListener.augmentPoll();
    }
}

