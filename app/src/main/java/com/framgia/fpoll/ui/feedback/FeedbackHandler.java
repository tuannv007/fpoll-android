package com.framgia.fpoll.ui.feedback;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <></>
 */
public class FeedbackHandler {
    private FeedbackContract.Presenter mListener;

    public FeedbackHandler(FeedbackContract.Presenter listener) {
        mListener = listener;
    }

    public void clickSendFeedback() {
        if (mListener != null) mListener.sendFeedback();
    }
}
