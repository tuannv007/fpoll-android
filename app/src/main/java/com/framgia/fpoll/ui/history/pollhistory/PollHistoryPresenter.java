package com.framgia.fpoll.ui.history.pollhistory;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.ui.history.PollHistoryType;
import com.framgia.fpoll.util.SharePreferenceUtil;

import java.util.List;

import static com.framgia.fpoll.util.Constant.DataConstant.DATA_PREFIX_TOKEN;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class PollHistoryPresenter implements PollHistoryContract.Presenter {
    private PollHistoryContract.View mView;
    private PollHistoryType mHistoryType;
    private ManagerRepository mRepository;
    private User mUser;

    public PollHistoryPresenter(PollHistoryContract.View view, PollHistoryType typeHistory,
                                ManagerRepository repository, SharePreferenceUtil preference) {
        mView = view;
        mRepository = repository;
        mHistoryType = typeHistory;
        mUser = preference.getUser();
        mView.start();
    }

    @Override
    public void getData() {
        mView.setLoadingTrue();
        switch (mHistoryType) {
            case INITIATE:
                mRepository.getHistory(DATA_PREFIX_TOKEN + mUser.getToken(),
                    new DataCallback<List<HistoryPoll>>() {
                        @Override
                        public void onSuccess(List<HistoryPoll> data) {
                            loadDataSuccess(data);
                        }

                        @Override
                        public void onError(String msg) {
                            loadDataError();
                        }
                    });
                break;
            case PARTICIPATE:
                mRepository.getPollParticipated(DATA_PREFIX_TOKEN + mUser.getToken(),
                    new DataCallback<List<HistoryPoll>>() {
                        @Override
                        public void onSuccess(List<HistoryPoll> data) {
                            loadDataSuccess(data);
                        }

                        @Override
                        public void onError(String msg) {
                            loadDataError();
                        }
                    });
                break;
            case CLOSE:
                mRepository.getPollClosed(DATA_PREFIX_TOKEN + mUser.getToken(),
                    new DataCallback<List<HistoryPoll>>() {
                        @Override
                        public void onSuccess(List<HistoryPoll> data) {
                            loadDataSuccess(data);
                        }

                        @Override
                        public void onError(String msg) {
                            loadDataError();
                        }
                    });
                break;
            default:
                break;
        }
    }

    private void loadDataError() {
        mView.showMessage(R.string.msg_not_load_item);
        mView.setLoadingFalse();
    }

    private void loadDataSuccess(List<HistoryPoll> data) {
        mView.setLoadingFalse();
        mView.setPollHistory(data);
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
