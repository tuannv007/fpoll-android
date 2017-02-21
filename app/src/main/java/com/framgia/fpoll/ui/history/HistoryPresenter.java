package com.framgia.fpoll.ui.history;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class HistoryPresenter implements HistoryContract.Presenter {
    private final HistoryContract.View mView;
    private ViewpagerType mViewpagerType;

    public HistoryPresenter(HistoryContract.View view, ViewpagerType viewpagerType) {
        mView = view;
        mViewpagerType = viewpagerType;
        mView.start();
    }

    @Override
    public void getAdapterType() {
        switch (mViewpagerType) {
            case HISTORY:
                mView.initAdapterHistory();
                break;
            case MANAGE:
                mView.initAdapterManage();
                break;
            case VOTE:
                mView.initAdapterVote();
                break;
            default:
                break;
        }
    }
}
