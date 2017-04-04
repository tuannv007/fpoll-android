package com.framgia.fpoll.ui.pollmanage.action;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public interface EditPollContract {
    interface View extends BaseView {
        void showMessage(String msg);
        void showMessage(int msg);
        void startModifyPoll(PollItem poll);
        void startUiPollCreation(PollItem data);
        void showProgressDialog();
        void hideProgressDialog();
        void viewHistory();
    }

    interface Presenter {
        void updateLinkPoll();
        void viewHistory();
        void editPoll();
        void closePoll();
        void createDuplicate();
        void deleteVoting();
        void loadData();
    }
}
