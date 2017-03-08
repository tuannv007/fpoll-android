package com.framgia.fpoll.ui.votemanager;

import com.framgia.fpoll.data.model.voteinfo.VoteInfo;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by anhtv on 08/03/2017.
 */
public interface LinkVoteContract {
    interface View extends BaseView {
        void showLoading();
        void hideLoading();
        void onGetVoteInfoSuccess(VoteInfo voteInfo);
        void onGetVoteInfoFailed();
    }

    interface Presenter {
        void getVoteInfo(String token);
    }
}
