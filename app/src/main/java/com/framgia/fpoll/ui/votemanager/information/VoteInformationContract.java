package com.framgia.fpoll.ui.votemanager.information;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.List;

/**
 * Created by anhtv on 23/02/2017.
 */
public interface VoteInformationContract {
    interface View extends BaseView {
        void onPostCommentSuccess(FpollComment comment);
        void onPostCommentFailed();
        void showEmptyError();
        void setLoading();
    }

    interface Presenter {
        void postComment(VoteInfoModel voteInfoModel);
    }
}
