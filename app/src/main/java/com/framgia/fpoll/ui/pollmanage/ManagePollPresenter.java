package com.framgia.fpoll.ui.pollmanage;

import com.framgia.fpoll.ui.history.ViewpagerType;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class ManagePollPresenter implements ManagePollContract.Presenter {
    private final ManagePollContract.View mView;
    private ViewpagerType mViewpagerType;

    public ManagePollPresenter(ManagePollContract.View view, ViewpagerType viewpagerType) {
        mView = view;
        mViewpagerType = viewpagerType;
        mView.start();
    }

    @Override
    public void initViewPage() {
        switch (mViewpagerType) {
            case VOTE:
                mView.startUiViewPageVote();
                break;
            case MANAGE:
            default:
                mView.startUiViewPageManage();
                break;
        }
    }
}
