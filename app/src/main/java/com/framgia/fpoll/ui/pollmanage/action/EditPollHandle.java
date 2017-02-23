package com.framgia.fpoll.ui.pollmanage.action;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public class EditPollHandle {
    private EditPollContract.Presenter mListener;

    public void submitUpdateEditLink() {
        if (mListener != null) mListener.submitUpdateEditLink();
    }

    public void submitUpdateVoteLink() {
        if (mListener != null) mListener.submitUpdateVoteLink();
    }
}
