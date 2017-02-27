package com.framgia.fpoll.ui.pollmanage.information;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class InformationHandler {
    private InformationContract.Presenter mListener;

    public InformationHandler(
        InformationContract.Presenter listener) {
        mListener = listener;
    }

    public void clickLinkVote() {
        if (mListener == null) return;
        mListener.clickLinkVote();
    }

    public void clickViewOption() {
        if (mListener == null) return;
        mListener.clickViewOption();
    }

    public void clickViewSetting() {
        if (mListener == null) return;
        mListener.clickViewSetting();
    }
}
