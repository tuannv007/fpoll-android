package com.framgia.fpoll.ui.history.pollhistory;

import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.poll.HistoryPoll;

/**
 * Created by Nhahv0902 on 2/23/2017.
 * <></>
 */
public class PollHistoryHandler {
    private PollHistoryContract.Presenter mListener;

    public PollHistoryHandler(@NonNull PollHistoryContract.Presenter listener) {
        mListener = listener;
    }

    public void openPollHistory(HistoryPoll item) {
        if (mListener == null) return;
        mListener.openPollHistory(item);
    }
}
