package com.framgia.fpoll.ui.pollmanage.history;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 3/24/2017.
 * <></>
 */
public interface HistoryManageContract {
    interface View extends BaseView {
        void onSuccess(DataInfoItem user);
        void showDialog();
        void dismissDialog();
        void onError(String msg);
    }

    interface Presenter {
        void getData(String token);
    }
}
