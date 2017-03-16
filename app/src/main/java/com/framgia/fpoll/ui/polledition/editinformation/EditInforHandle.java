package com.framgia.fpoll.ui.polledition.editinformation;

import com.framgia.fpoll.ui.pollcreation.infomation.CreationContract;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforHandle {
    EditInforContract.Presenter mListener;

    public EditInforHandle(EditInforContract.Presenter listener) {
        mListener = listener;
    }

    public void showDatePicker() {
        if (mListener != null) mListener.showDatePicker();
    }

    public void nextStep() {
        if (mListener != null) mListener.nextStep();
    }
}
