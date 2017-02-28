package com.framgia.fpoll.ui.history.pollhistory;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;
import com.framgia.fpoll.data.source.local.DataSource;
import com.framgia.fpoll.data.source.local.PollHistoryRepository;

import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class PollHistoryPresenter implements PollHistoryContract.Presenter {
    private PollHistoryContract.View mView;
    private PollHistoryType mHistoryType;
    private PollHistoryRepository mRepository;

    public PollHistoryPresenter(PollHistoryContract.View view, PollHistoryType typeHistory,
                                PollHistoryRepository repository) {
        mView = view;
        mHistoryType = typeHistory;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void getData() {
        mView.setLoadingTrue();
        mRepository.getPollHistory(mHistoryType, new DataSource.GetCallback<PollHistoryItem>() {
            @Override
            public void onLoaded(List<PollHistoryItem> data) {
                mView.setLoadingFalse();
                mView.setPollHistory(data);
            }

            @Override
            public void onNotAvailable() {
                mView.setLoadingFalse();
            }
        });
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
