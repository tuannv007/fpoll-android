package com.framgia.fpoll.ui.votemanager.information;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.networking.api.VoteInfoAPI;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by anhtv on 23/02/2017.
 */
public class VoteInformationPresenter implements VoteInformationContract.Presenter {

    private VoteInformationContract.View mView;
    private ObservableField<FpollComment> mFpollComment = new ObservableField<>();
    private VoteInfoRepository mVoteInfoRepository;
    private ObservableBoolean mIsLogin = new ObservableBoolean();

    public VoteInformationPresenter(VoteInformationContract.View view,
            VoteInfoRepository voteInfoRepository, SharePreferenceUtil preference) {
        mView = view;
        mVoteInfoRepository = voteInfoRepository;
        mIsLogin.set(preference.isLogin());
        mView.start();
        mFpollComment.set(new FpollComment());
    }

    @Override
    public void postComment(@NonNull VoteInfoModel voteInfoModel) {
        int idPoll = voteInfoModel.getVoteInfo().getPoll().getId();
        String userName = voteInfoModel.getVoteInfo().getPoll().getUser().getUsername();
        String content = mFpollComment.get().getContent();
        if (TextUtils.isEmpty(content)) {
            mView.showEmptyError();
        } else {
            mView.setLoading();
            VoteInfoAPI.CommentBody commentBody =
                    new VoteInfoAPI.CommentBody(userName, idPoll, content);
            mVoteInfoRepository.postComment(commentBody, new DataCallback<FpollComment>() {
                @Override
                public void onSuccess(FpollComment data) {
                    mView.onPostCommentSuccess(mFpollComment.get());
                    mFpollComment.set(new FpollComment());
                }

                @Override
                public void onError(String msg) {
                    mView.onPostCommentFailed();
                }
            });
        }
    }

    public ObservableField<FpollComment> getFpollComment() {
        return mFpollComment;
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
