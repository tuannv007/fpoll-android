package com.framgia.fpoll.ui.poll;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedHandlerAction {
    public PollCreatedPresenter mListener;

    public PollCreatedHandlerAction(PollCreatedPresenter presenter) {
        this.mListener = presenter;
    }

    public void reSendEmail() {
        if (mListener == null) return;
        mListener.resendEmail();
    }

    public void copyLink() {
        if (mListener == null) return;
        mListener.copyLink();
    }

    public void viewManagerPoll(String idPoll) {
        if (mListener == null) return;
        mListener.viewManagerLink(idPoll);
    }
}
