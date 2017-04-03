package com.framgia.fpoll.ui.pollmanage;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class ManagePollPresenter implements ManagePollContract.Presenter {
    private final ManagePollContract.View mView;
    private ManagerRepository mRepository;

    public ManagePollPresenter(ManagePollContract.View view, ManagerRepository repository) {
        mView = view;
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
