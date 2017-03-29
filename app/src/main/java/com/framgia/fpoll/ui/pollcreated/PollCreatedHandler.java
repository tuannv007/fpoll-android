package com.framgia.fpoll.ui.pollcreated;

import android.support.annotation.NonNull;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedHandler {
    public PollCreatedContract.Presenter mListener;

    public PollCreatedHandler(@NonNull PollCreatedContract.Presenter presenter) {
        mListener = presenter;
    }

    public void reSendEmail(int pollId) {
        if (mListener != null) mListener.resendEmail(pollId);
    }

    public void copyLinkInvite() {
        if (mListener != null) mListener.copyLinkInvite();
    }

    public void copyLinkManager() {
        if (mListener != null) mListener.copyLinkManager();
    }

    public void viewManagerPoll() {
        if (mListener != null) mListener.viewLinkManager();
    }

    public void clickViewInvite() {
        if (mListener != null) mListener.viewLinkInvite();
    }
}
