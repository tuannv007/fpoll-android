package com.framgia.fpoll.ui.pollhistory;

import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.PollHistoryItem;

/**
 * Created by Nhahv0902 on 2/23/2017.
 * <></>
 */
public class PollHistoryHandler {
    private PollHistoryContract.Presenter mListener;

    public PollHistoryHandler(@NonNull PollHistoryContract.Presenter listener) {
        mListener = listener;
    }

    public void clickPollHistory(PollHistoryItem item) {
        if (mListener == null) return;
        mListener.clickPollHistory(item);
    }
}
