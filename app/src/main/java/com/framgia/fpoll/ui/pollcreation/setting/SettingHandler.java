package com.framgia.fpoll.ui.pollcreation.setting;

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

    public void clickShowPassword() {
        if (mListener != null) mListener.onShowPassword();
    }

    public void clickMinus() {
        if (mListener != null) mListener.clickMinus();
    }

    public void clickAugment() {
        if (mListener != null) mListener.clickAugment();
    }

    public void changeAllowEdit(boolean checked) {
       if (mListener != null) mListener.changeAllowEditPoll(checked);
    }

    public void changeAllowAddAnswer(boolean checked) {
       if (mListener != null) mListener.changeAllowAddAnswer(checked);
    }
}
