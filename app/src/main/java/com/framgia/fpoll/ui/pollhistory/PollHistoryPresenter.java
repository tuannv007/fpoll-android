package com.framgia.fpoll.ui.pollhistory;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class PollHistoryPresenter
    implements PollHistoryContract.Presenter {
    private PollHistoryContract.View mView;
    private PollHistoryType mHistoryType;

    public PollHistoryPresenter(PollHistoryContract.View view, PollHistoryType typeHistory) {
        mView = view;
        mHistoryType = typeHistory;
        mView.start();
    }

    @Override
    public void getData() {
        mView.setLoadingTrue();
        List<PollHistoryItem> pollHistories = new ArrayList<>();
        switch (mHistoryType) {
            case INITIATE:
                // TODO: 2/16/2017  refresh data of tab poll initiated
                break;
            case PARTICIPATE:
                // TODO: 2/16/2017  refresh data of tab poll participate
                break;
            case CLOSE:
                // TODO: 2/16/2017  refresh data of tab poll close
                break;
            default:
                break;
        }
        mView.setLoadingFalse();
        mView.setPollHistory(pollHistories);
    }
}
