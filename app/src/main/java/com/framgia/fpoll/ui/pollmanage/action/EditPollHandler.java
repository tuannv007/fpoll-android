package com.framgia.fpoll.ui.pollmanage.action;

import android.support.annotation.NonNull;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

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

    public void viewHistory(FloatingActionsMenu menu) {
        if (mListener == null) return;
        menu.collapse();
        mListener.viewHistory();
    }

    public void editPoll(FloatingActionsMenu menu) {
        if (mListener == null) return;
        menu.collapse();
        mListener.editPoll();
    }

    public void closePoll(FloatingActionsMenu menu) {
        if (mListener == null) return;
        menu.collapse();
        mListener.closePoll();
    }

    public void createDuplicate(FloatingActionsMenu menu) {
        if (mListener == null) return;
        menu.collapse();
        mListener.createDuplicate();
    }

    public void deleteVoting(FloatingActionsMenu menu) {
        if (mListener == null) return;
        menu.collapse();
        mListener.deleteVoting();
    }
}
