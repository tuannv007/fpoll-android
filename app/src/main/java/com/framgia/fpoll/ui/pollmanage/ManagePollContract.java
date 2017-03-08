package com.framgia.fpoll.ui.pollmanage;

import android.support.v4.app.Fragment;

import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;
import com.framgia.fpoll.data.ApiRestClient.APIService.pollmanager.DataInfoItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public interface ManagePollContract {
    interface View extends BaseView {
        void getDataFromIntent();
        void addFragment(Fragment fragment, int resTitle);
        void startUiViewPageVote();
        void startUiViewPageManage();
        void onSuccess(ResponseItem<DataInfoItem> pollInfoList);
        void onError(String message);
        void showDialog();
        void dismissDialog();
    }

    interface Presenter {
        void initViewPage();
        void getAllData(String token);
    }
}
