package com.framgia.fpoll.ui.introductapp;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceHandlerAction {
    private IntroduceAppPresenter mListener;

    public IntroduceHandlerAction(IntroduceAppPresenter listener) {
        mListener = listener;
    }

    public void openFacebook() {
        if (mListener == null) return;
        mListener.openFacebook();
    }

    public void openGitHub() {
        if (mListener == null) return;
        mListener.openGitHub();
    }

    public void openLinkeDin() {
        if (mListener == null) return;
        mListener.openLikeDin();
    }
}
