package com.framgia.fpoll.ui.pollmanage.history;

import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;

/**
 * Created by Nhahv0902 on 3/24/2017.
 * >
 */
public class HistoryManagePresenter implements HistoryManageContract.Presenter {
    private final HistoryManageContract.View mView;
    private ManagerRepository mRepository;

    public HistoryManagePresenter(HistoryManageContract.View view, ManagerRepository repository) {
        mView = view;
        mRepository = repository;
    }
}
