package com.framgia.fpoll.ui.history.pollhistory;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.ui.history.PollHistoryType;

import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class PollHistoryPresenter implements PollHistoryContract.Presenter {
    private PollHistoryContract.View mView;
    private PollHistoryType mHistoryType;
    private ManagerRepository mRepository;
    private String mToken = "";

    public PollHistoryPresenter(PollHistoryContract.View view, PollHistoryType typeHistory,
                                ManagerRepository repository) {
        mView = view;
        mRepository = repository;
        mHistoryType = typeHistory;
        mView.start();
    }

    @Override
    public void getData() {
        mView.setLoadingTrue();
        mRepository.getHistory(mToken, new DataCallback<List<HistoryPoll>>() {
            @Override
            public void onSuccess(List<HistoryPoll> data) {
                mView.setLoadingFalse();
                mView.setPollHistory(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(R.string.msg_not_load_item);
                mView.setLoadingFalse();
            }
        });
    }

    @Override
    public void clickPollHistory(HistoryPoll pollHistoryItem) {
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
