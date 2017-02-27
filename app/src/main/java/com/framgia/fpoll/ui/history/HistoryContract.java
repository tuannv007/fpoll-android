package com.framgia.fpoll.ui.history;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public interface HistoryContract {
    interface View extends BaseView {
        void getDataFromActivity();
        void initAdapterHistory();
        void initAdapterManage();
        void initAdapterVote();
    }

    interface Presenter {
        void getAdapterType();
    }
}
