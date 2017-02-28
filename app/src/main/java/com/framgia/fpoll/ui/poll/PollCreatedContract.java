package com.framgia.fpoll.ui.poll;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/21/17.
 */
public interface PollCreatedContract {
    interface View extends BaseView {
        void copyUrl();
    }

    interface Presenter {
        void copyLink();
        void viewManagerLink(String idPoll);
        void resendEmail();
    }
}
