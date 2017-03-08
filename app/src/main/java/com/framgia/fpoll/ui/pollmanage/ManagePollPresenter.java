package com.framgia.fpoll.ui.pollmanage;

import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;
import com.framgia.fpoll.data.ApiRestClient.APIService.pollmanager.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanagerinfo.PollInfoRepository;
import com.framgia.fpoll.ui.history.ViewpagerType;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class ManagePollPresenter implements ManagePollContract.Presenter {
    private final ManagePollContract.View mView;
    private ViewpagerType mViewpagerType;
    private PollInfoRepository mRepository;

    public ManagePollPresenter(ManagePollContract.View view, ViewpagerType viewpagerType,
                               PollInfoRepository repository) {
        mView = view;
        mViewpagerType = viewpagerType;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void initViewPage() {
        switch (mViewpagerType) {
            case VOTE:
                mView.startUiViewPageVote();
                break;
            default:
                mView.startUiViewPageManage();
                break;
        }
    }

    public void getAllData(String token) {
        mView.showDialog();
        mRepository.loadData(token, new DataCallback<ResponseItem<DataInfoItem>>() {
            @Override
            public void onSuccess(ResponseItem<DataInfoItem> pollInfoList) {
                mView.onSuccess(pollInfoList);
                mView.dismissDialog();
            }

            @Override
            public void onError(String message) {
                mView.onError(message);
                mView.dismissDialog();
            }
        });
    }
}
