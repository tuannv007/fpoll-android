package com.framgia.fpoll.ui.pollsetting;

/**
 * Created by framgia on 23/02/2017.
 * <></>
 */
public class SettingHandler {
    private SettingPollContract.Presenter mListener;

    public SettingHandler(SettingPollContract.Presenter listener) {
        mListener = listener;
    }

    public void nextStep() {
        if (mListener != null) mListener.nextStep();
    }

    public void previousStep() {
        if (mListener != null) mListener.previousStep();
    }

    public void checkedVotingResult(boolean checked) {
        if (mListener != null) mListener.onCheckedVotingResult(checked);
    }
}
