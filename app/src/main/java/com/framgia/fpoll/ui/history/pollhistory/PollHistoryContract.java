package com.framgia.fpoll.ui.history.pollhistory;

import com.framgia.fpoll.data.model.PollHistoryItem;
import com.framgia.fpoll.ui.base.BaseView;

import java.util.List;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public interface PollHistoryContract {
    interface View extends BaseView {
        void setPollHistory(List<PollHistoryItem> pollHistories);
        void setLoadingTrue();
        void setLoadingFalse();
        void clickOpenManagePoll(PollHistoryItem pollHistoryItem);
        void clickReopenPoll(PollHistoryItem pollHistoryItem);
    }

    interface Presenter {
        void getData();
        void clickPollHistory(PollHistoryItem pollHistoryItem);
    }
}
