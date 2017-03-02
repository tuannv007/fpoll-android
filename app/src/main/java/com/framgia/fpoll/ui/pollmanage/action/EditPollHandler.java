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

    public void clickSaveLinkManager() {
        if (mListener != null) mListener.submitSaveLinkManager();
    }

    public void clickSaveLinkVoting() {
        if (mListener != null) mListener.submitSaveLinkVoting();
    }

    public void clickEditPoll(EditPollType type) {
        if (mListener == null) return;
        switch (type) {
            case VIEW_HISTORY:
                mListener.viewHistory();
                break;
            case EDIT:
                mListener.editPoll();
                break;
            case CLOSE:
                mListener.closePoll();
                break;
            case CREATE:
                mListener.createDuplicate();
                break;
            default:
                break;
        }
    }
}
