package com.framgia.fpoll.ui.polledition;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 15/03/2017.
 */
public interface ModifyPollContract {
    interface View extends BaseView {
        void previousUI();

        void nextUI();

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void submitEditPoll();

        void showMessage(int message);
    }

    interface Presenter {
        void onClickSave();

        void onClickNext();

        void onClickPrevious();

        void submitEditInformation();

        void submitEditOption();

        void submitEditSetting();
    }
}
