package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.model.VoteItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public interface VoteContract {
    interface View extends BaseView {
        void updateVoteChoice(OptionModel option);
        void onSubmitSuccess();
        void onSubmitFailed();
    }

    interface Presenter {
        void voteOption(OptionModel option);
        void submitVote(VoteInfoModel voteInfoModel);
    }
}
