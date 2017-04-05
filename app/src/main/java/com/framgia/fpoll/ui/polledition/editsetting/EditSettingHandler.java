package com.framgia.fpoll.ui.polledition.editsetting;

/**
 * Created by framgia on 17/03/2017.
 */
public class EditSettingHandler {
    private EditSettingContract.Presenter mListener;

    public EditSettingHandler(EditSettingContract.Presenter listener) {
        mListener = listener;
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
