package com.framgia.fpoll.ui.pollcreation;

import com.android.annotations.NonNull;

/**
 * Created by Nhahv0902 on 3/28/2017.
 * <></>
 */
public class PollCreationPresenter implements PollCreationContract.Presenter {
    private final PollCreationContract.View mView;

    public PollCreationPresenter(@NonNull PollCreationContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void previous() {
        if (mView != null) mView.previousUI();
    }

    @Override
    public void next() {
        if (mView != null) mView.nextUI();
    }

    @Override
    public void finish() {
        if (mView != null) mView.finishCreate();
    }
}
