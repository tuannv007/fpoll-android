package com.framgia.fpoll.ui.pollmanage;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.ui.history.ViewpagerType;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class ManagePollPresenter implements ManagePollContract.Presenter {
    private final ManagePollContract.View mView;
    private ViewpagerType mViewpagerType;
    private ManagerRepository mRepository;

    public ManagePollPresenter(ManagePollContract.View view, ViewpagerType viewpagerType,
                               ManagerRepository repository) {
        mView = view;
        mViewpagerType = viewpagerType;
        mRepository = repository;
        mView.start();
    }


    public void getAllData(@NonNull String token) {
        mView.showDialog();
        mRepository.getPoll(token, new DataCallback<DataInfoItem>() {
                @Override
                public void onSuccess(DataInfoItem data) {
                    mView.onSuccess(data);
                    mView.dismissDialog();
                }

                @Override
                public void onError(String msg) {
                    mView.onError(msg);
                    mView.dismissDialog();
                }
            }
        );
    }
}
