package com.framgia.fpoll.ui.history.pollhistory;

import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.ui.base.BaseView;

import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public interface PollHistoryContract {
    interface View extends BaseView {
        void setPollHistory(List<HistoryPoll> pollHistories);
        void setLoadingTrue();
        void setLoadingFalse();
        void clickOpenManagePoll(String token);
        void showMessage(int res);
        void showMessage(String res);
    }

    interface Presenter {
        void getData();
        void clickPollHistory(HistoryPoll pollHistoryItem);
    }
}
