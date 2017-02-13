package com.framgia.fpoll.ui.pollhistory;

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
    }

    interface Presenter {
        void getData();
    }
}
