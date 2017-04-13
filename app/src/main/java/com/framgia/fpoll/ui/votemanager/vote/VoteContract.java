package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import java.util.List;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public interface VoteContract {
    interface View extends BaseView {
        void updateVoteChoice(Option option);

        void onSubmitSuccess(List<Option> options);

        void onSubmitFailed(String messages);

        void onNotifyVote();

        void setLoading(boolean isShow);

        void showGallery();

        void updateAdditionOptionSuccess();

        void showVoteRequirement(int msg);

        void resetChoiceBox();

        void onCheckedChanged(boolean check);

        void showDialog();

        void dismissDialog();
    }

    interface Presenter {
        void voteOption(Option option);

        void submitVote(VoteInfoModel voteInfoModel);

        void openGallery();

        void setImageOption(String imagePath);

        void updateAdditionOption(VoteInfoModel voteInfoModel);

        void cleanOption();
    }
}
