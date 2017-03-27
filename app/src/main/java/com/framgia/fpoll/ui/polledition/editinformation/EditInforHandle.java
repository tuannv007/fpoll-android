package com.framgia.fpoll.ui.polledition.editinformation;

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

    public void nextStep(int id) {
        if (mListener != null) mListener.nextStep(id);
    }

    public void back() {
        if (mListener != null) mListener.back();
    }
}
