package com.framgia.fpoll.ui.votemanager.result;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by anhtv on 15/03/2017.
 */
public interface LinkVoteResultContract {
    interface View extends BaseView {
        void showDetail(Option option);
    }

    interface Presenter {
        void openVoteDetail(Option option);
    }
}
