package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.data.model.poll.Option;

/**
 * Created by framgia on 22/02/2017.
 * <></>
 */
public class OptionHandler {
    private OptionPollContract.Presenter mListener;

    public OptionHandler(OptionPollContract.Presenter listener) {
        mListener = listener;
    }

    public void clickPickImage(Option optionItem, int position) {
        if (mListener != null) mListener.pickImage(optionItem, position);
    }

    public void clickPickDate(Option optionItem, int position) {
        if (mListener != null) mListener.pickDate(optionItem, position);
    }

    public void clickDeletePoll(Option optionItem, int position) {
        if (mListener != null) mListener.deletePoll(optionItem, position);
    }

    public void clickAugmentPoll() {
        if (mListener != null) mListener.augmentPoll();
    }
}

