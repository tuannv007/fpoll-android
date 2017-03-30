package com.framgia.fpoll.ui.pollmanage.information;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public interface PollInformationContract {
    interface View extends BaseView {
        void startUiVoting();
        void showDialogOption();
        void showDialogSetting();
        void saveSuccess(String message);
        void onError(String message);
        void showDateTimePicker();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void clickLinkVote();
        void clickViewOption();
        void clickViewSetting();
        void saveInformation(int id);
        void showDateTimePicker();
    }
}
