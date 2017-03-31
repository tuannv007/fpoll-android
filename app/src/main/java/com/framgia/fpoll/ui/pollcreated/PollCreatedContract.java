package com.framgia.fpoll.ui.pollcreated;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/21/17.
 */
public interface PollCreatedContract {
    interface View extends BaseView {
        void copyLinkInvite();
        void startUiPollManager();
        void copyLinkManager();
        void showMessage(String msg);
        void startUiLinkInviteVote();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void copyLinkInvite();
        void viewLinkInvite();
        void resendEmail(int idPoll);
        void copyLinkManager();
        void viewLinkManager();
    }
}
