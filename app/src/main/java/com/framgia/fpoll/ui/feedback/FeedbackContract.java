package com.framgia.fpoll.ui.feedback;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <></>
 */
public interface FeedbackContract {
    interface View extends BaseView {
        void sendFeedbackSuccess();

        void showMessage(int resString);

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter {
        void sendFeedback();
    }
}
