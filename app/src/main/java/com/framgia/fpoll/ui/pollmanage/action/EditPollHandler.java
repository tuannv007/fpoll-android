package com.framgia.fpoll.ui.pollmanage.action;

import android.support.annotation.NonNull;

/**
 * Created by tran.trung.phong on 01/03/2017.
 * <></>
 */
public class EditPollHandler {
    private EditPollContract.Presenter mListener;

    public EditPollHandler(@NonNull EditPollContract.Presenter listener) {
        mListener = listener;
    }

    public void clickUpdateLinkPoll() {
        if (mListener != null) mListener.updateLinkPoll();
    }

    public void viewHistory() {
        if (mListener != null) mListener.viewHistory();
    }

    public void editPoll() {
        if (mListener != null) mListener.editPoll();
    }

    public void closePoll() {
        if (mListener != null) mListener.closePoll();
    }

    public void createDuplicate() {
        if (mListener != null) mListener.createDuplicate();
    }

    public void deleteVoting() {
        if (mListener != null) mListener.deleteVoting();
    }
}
