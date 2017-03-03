package com.framgia.fpoll.ui.poll;

import android.support.annotation.NonNull;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedHandler {
    public PollCreatedContract.Presenter mListener;

    public PollCreatedHandler(@NonNull PollCreatedContract.Presenter presenter) {
        mListener = presenter;
    }

    public void reSendEmail() {
        if (mListener == null) return;
        mListener.resendEmail();
    }

    public void copyLinkInvite() {
        if (mListener != null) mListener.copyLinkInvite();
    }

    public void copyLinkManager() {
        if (mListener != null) mListener.copyLinkManager();
    }

    public void viewManagerPoll(String idPoll) {
        if (mListener != null) mListener.viewLinkManager(idPoll);
    }

    public void clickViewInvite(String idPoll) {
        if (mListener != null) mListener.viewLinkInvite(idPoll);
    }
}
