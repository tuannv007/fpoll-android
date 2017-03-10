package com.framgia.fpoll.ui.votemanager.information;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.databinding.FragmentVoteInfoBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.List;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <.
 */
public class VoteInformationFragment extends Fragment implements VoteInformationContract.View {
    private static final String ARGUMENT_VOTE_INFO = "ARGUMENT_VOTE_INFO";
    private FragmentVoteInfoBinding mBinding;
    private VoteInformationContract.Presenter mPresenter;
    private VoteInfoModel mVoteInfoModel;
    private ObservableBoolean mCommentExpand = new ObservableBoolean();
    private ObservableField<CommentAdapter> mAdapter = new ObservableField<>();

    public static VoteInformationFragment newInstance(VoteInfoModel voteInfo) {
        VoteInformationFragment voteInformationFragment = new VoteInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_VOTE_INFO, voteInfo);
        voteInformationFragment.setArguments(bundle);
        return voteInformationFragment;
    }

    /**
     * TODO get PollInfo by intent
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mVoteInfoModel = (VoteInfoModel) getArguments().getSerializable(ARGUMENT_VOTE_INFO);
        mPresenter = new VoteInformationPresenter(this);
        mAdapter.set(new CommentAdapter(mPresenter));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentVoteInfoBinding.inflate(inflater, container, false);
        mBinding.setVoteInfoModel(mVoteInfoModel);
        mBinding.layoutPollInfo.setVoteInfoModel(mVoteInfoModel);
        mBinding.layoutPostComment.setPresenter((VoteInformationPresenter) mPresenter);
        mBinding.layoutComments.setFragment(this);
        mBinding.layoutComments.setVoteInfoModel(mVoteInfoModel);
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

    public ObservableBoolean getCommentExpand() {
        return mCommentExpand;
    }

    public CommentAdapter getAdapter() {
        return mAdapter.get();
    }
}
