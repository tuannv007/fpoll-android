package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.List;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public interface VoteContract {
    interface View extends BaseView {
        void updateVoteChoice(OptionModel option);
        void onSubmitSuccess(List<String> messages);
        void onSubmitFailed(String messages);
        void onNotifyVote();
        void setLoading(boolean isShow);
    }

    interface Presenter {
        void voteOption(OptionModel option);
        void submitVote(VoteInfoModel voteInfoModel);
    }
}
