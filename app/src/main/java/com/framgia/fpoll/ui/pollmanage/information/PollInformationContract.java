package com.framgia.fpoll.ui.pollmanage.information;

import com.framgia.fpoll.data.model.DataInfoItem;
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

        void showMessage(String message);

        void showMessage(int message);

        void showDateTimePicker();

        void showProgress();

        void hideProgress();

        void onGetPollSuccessful(DataInfoItem data);

        void onGetPollFailed(String message);
    }

    interface Presenter {
        void clickLinkVote();

        void clickViewOption();

        void clickViewSetting();

        void saveInformation();

        void showDateTimePicker();

        void loadData();
    }
}
