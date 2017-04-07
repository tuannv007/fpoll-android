package com.framgia.fpoll.ui.history;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class HistoryPresenter implements HistoryContract.Presenter {
    private final HistoryContract.View mView;

    public HistoryPresenter(HistoryContract.View view) {
        mView = view;
        mView.start();
    }
}
