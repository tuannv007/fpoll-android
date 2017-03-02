package com.framgia.fpoll.ui.votemanager;

/**
 * Created by Nhahv0902 on 3/2/2017.
 * <></>
 */
public class VoteResultPresenter implements VoteResultContract.Presenter {
    private final VoteResultContract.View mView;
    private VoteResultType mVoteResultType;

    public VoteResultPresenter(VoteResultContract.View view, VoteResultType voteResultType) {
        mView = view;
        mVoteResultType = voteResultType;
        mView.start();
    }

    @Override
    public void initFragment() {
        switch (mVoteResultType) {
            case PIE_CHAR:
                mView.addPieChartFragment();
                break;
            case BAR_CHART:
                mView.addBarChartFragment();
                break;
            case TABLE:
            default:
                mView.addTableFragment();
                break;
        }
    }

    @Override
    public void updateVoteResultType(VoteResultType type) {
        mVoteResultType = type;
    }
}
