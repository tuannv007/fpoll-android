package com.framgia.fpoll.ui.pollhistory;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class PollHistoryPresenter implements PollHistoryContract.Presenter {
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
                // TODO: 2/24/2017 get list of initiate poll
                break;
            case PARTICIPATE:
                // TODO: 2/24/2017 get list of participate poll
                break;
            case CLOSE:
                // TODO: 2/24/2017 get list of close poll
                break;
            default:
                break;
        }
        mView.setLoadingFalse();
        mView.setPollHistory(pollHistories);
    }

    @Override
    public void clickPollHistory(PollHistoryItem pollHistoryItem) {
        switch (mHistoryType) {
            case INITIATE:
            case PARTICIPATE:
                mView.clickOpenManagePoll(pollHistoryItem);
                break;
            case CLOSE:
                mView.clickReopenPoll(pollHistoryItem);
                break;
            default:
                break;
        }
    }
}
