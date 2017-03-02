package com.framgia.fpoll.ui.votemanager;

import android.support.v4.app.Fragment;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 3/2/2017.
 * <></>
 */
public interface VoteResultContract {
    interface View extends BaseView {
        void addPieChartFragment();
        void addBarChartFragment();
        void addTableFragment();
    }

    interface Presenter {
        void initFragment();
        void updateVoteResultType(VoteResultType type);
    }
}
