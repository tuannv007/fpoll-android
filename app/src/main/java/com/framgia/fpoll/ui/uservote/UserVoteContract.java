package com.framgia.fpoll.ui.uservote;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 4/3/17.
 */
public interface UserVoteContract {
    interface View extends BaseView {
        void nextToVote();
    }

    interface Presenter {
    }
}
