package com.framgia.fpoll.ui.pollmanage.history;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by Nhahv0902 on 3/24/2017.
 * >
 */
public class HistoryManagePresenter implements HistoryManageContract.Presenter {
    private final HistoryManageContract.View mView;
    private PollRepository mRepository;

    public HistoryManagePresenter(HistoryManageContract.View view, PollRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getData(String token) {
        mView.showDialog();
        mRepository.getActivity(token, new DataCallback<ResponseItem<DataInfoItem>>() {
            @Override
            public void onSuccess(ResponseItem<DataInfoItem> data) {
                mView.onSuccess(data.getData());
                mView.dismissDialog();
            }

            @Override
            public void onError(String msg) {
                mView.onError(msg);
                mView.dismissDialog();
            }
        });
    }
}
