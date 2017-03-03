package com.framgia.fpoll.ui.votemanager.information;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.framgia.fpoll.data.model.FpollComment;

import java.util.ArrayList;

/**
 * Created by anhtv on 23/02/2017.
 */
public class VoteInformationPresenter implements VoteInformationContract.Presenter {
    private VoteInformationContract.View mView;
    private ObservableField<FpollComment> mFpollComment = new ObservableField<>();

    public VoteInformationPresenter(VoteInformationContract.View view) {
        mView = view;
        mView.start();
        mFpollComment.set(new FpollComment());
    }

    @Override
    public void getComments() {
        mView.onGetCommentsSuccess(new ArrayList<FpollComment>());
        /**
         * TODO Get comments failed from network success
         */
    }

    @Override
    public void notifyEmpty() {
        mView.showEmptyError();
    }

    @Override
    public void postComment() {
        String userName = mFpollComment.get().getUserName();
        String content = mFpollComment.get().getContent();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(content)) mView.showEmptyError();
        else {
            /**
             * TODO post comment to server
             */
            mView.onPostCommentSuccess(mFpollComment.get());
        }
    }

    public FpollComment getFpollComment() {
        return mFpollComment.get();
    }
}
