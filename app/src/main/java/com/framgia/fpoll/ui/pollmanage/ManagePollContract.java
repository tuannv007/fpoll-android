package com.framgia.fpoll.ui.pollmanage;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public interface ManagePollContract {
    interface View extends BaseView {
        void getDataFromIntent();
        void onSuccess(DataInfoItem data);
        void onError(String message);
        void showDialog();
        void dismissDialog();
    }

    interface Presenter {
        void getAllData(String token);
    }
}
