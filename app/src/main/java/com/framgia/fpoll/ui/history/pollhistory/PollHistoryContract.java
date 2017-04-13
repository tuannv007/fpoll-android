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

        void onOpenManagerPollClick(String token);

        void onOpenVoteClick(String token);

        void showMessage(int res);

        void showMessage(String res);

        void showDialog();

        void hideDialog();

        void showConfirmDialog(HistoryPoll historyPoll);

        void showPollClosedDialog();
    }

    interface Presenter {
        void getData();

        void openPollHistory(HistoryPoll pollHistoryItem);

        void reopenPoll(HistoryPoll historyPoll);
    }
}
