package com.framgia.fpoll.ui.introduction;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceHandlerAction {
    private IntroduceAppContract.Presenter mListener;

    public IntroduceHandlerAction(IntroduceAppContract.Presenter listener) {
        mListener = listener;
    }

    public void onPageChange(int pagePosition) {
        if (mListener == null) return;
        mListener.onPageChange(pagePosition);
    }
}
