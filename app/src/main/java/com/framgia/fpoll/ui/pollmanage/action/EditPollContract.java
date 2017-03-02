package com.framgia.fpoll.ui.pollmanage.action;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public interface EditPollContract {
    interface View extends BaseView {
    }

    interface Presenter {
        void submitUpdateEditLink();
        void submitUpdateVoteLink();
    }
}
