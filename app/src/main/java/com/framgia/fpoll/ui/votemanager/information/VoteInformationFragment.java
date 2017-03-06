package com.framgia.fpoll.ui.votemanager.information;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.PollInfo;
import com.framgia.fpoll.databinding.FragmentVoteInfoBinding;
import com.framgia.fpoll.databinding.PartialCommentsBinding;
import com.framgia.fpoll.databinding.PartialPollInfoBinding;
import com.framgia.fpoll.databinding.PartialPostCommentBinding;

import java.util.List;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <.
 */
public class VoteInformationFragment extends Fragment implements VoteInformationContract.View {
    private FragmentVoteInfoBinding mBinding;
    private VoteInformationContract.Presenter mPresenter;
    private PollInfo mPollInfo;
    private ObservableBoolean mCommentExpand = new ObservableBoolean();
    private ObservableField<CommentAdapter> mAdapter = new ObservableField<>();

    public static VoteInformationFragment newInstance() {
        return new VoteInformationFragment();
    }

    /**
     * TODO get PollInfo by intent
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPollInfo = new PollInfo();
        mPresenter = new VoteInformationPresenter(this);
        mAdapter.set(new CommentAdapter(mPresenter));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentVoteInfoBinding.inflate(inflater, container, false);
        mBinding.layoutPollInfo.setFragment(this);
        mBinding.layoutPostComment.setPresenter((VoteInformationPresenter) mPresenter);
        mBinding.layoutComments.setFragment(this);
        return mBinding.getRoot();
    }

    public void setCommentsView() {
        mCommentExpand.set(!mCommentExpand.get());
        if (mCommentExpand.get()) mPresenter.getComments();
    }

    @Override
    public void start() {
        mCommentExpand.set(false);
    }

    @Override
    public void onGetCommentsSuccess(List<FpollComment> list) {
        if (list != null) mAdapter.get().updateComments(list);
    }

    @Override
    public void onGetCommentFailed() {
        /**
         * TODO get comment failed
         */
    }

    @Override
    public void onPostCommentSuccess(FpollComment comment) {
        mAdapter.get().insertComments(comment);
    }

    @Override
    public void onPostCommentFailed() {
        /**
         * TODO post comment failed
         */
    }

    @Override
    public void showEmptyError() {
        mBinding.layoutPostComment.setMsgError(getString(R.string.msg_content_error));
    }

    public PollInfo getPollInfo() {
        return mPollInfo;
    }

    public ObservableBoolean getCommentExpand() {
        return mCommentExpand;
    }

    public CommentAdapter getAdapter() {
        return mAdapter.get();
    }
}
