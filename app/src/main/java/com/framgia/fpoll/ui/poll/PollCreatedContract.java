package com.framgia.fpoll.ui.poll;

import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 2/21/17.
 */
public interface PollCreatedContract {
    interface View extends BaseView {
        void copyLinkInvite();
        void startUiPollManager();
        void copyLinkManager();
        void resentSuccess(ResponseItem data);
        void resentError(String msg);
        void startUiLinkInviteVote();
    }

    interface Presenter {
        void copyLinkInvite();
        void viewLinkInvite(String idPoll);
        void resendEmail(int idPoll);
        void copyLinkManager();
        void viewLinkManager();
    }
}
