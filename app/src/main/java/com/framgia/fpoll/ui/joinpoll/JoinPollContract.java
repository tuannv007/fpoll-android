package com.framgia.fpoll.ui.joinpoll;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 4/3/17.
 */
public interface JoinPollContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showMessage(String msg);

        void startUIManager(DataInfoItem poll);

        void startUIVote(DataInfoItem poll);
    }

    interface Presenter {
    }
}
