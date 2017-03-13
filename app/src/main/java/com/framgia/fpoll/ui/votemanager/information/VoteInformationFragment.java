package com.framgia.fpoll.ui.votemanager.information;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.FragmentVoteInfoBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.ItemStatus;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

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
    private CommentAdapter mCommentAdapter;

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
        mPresenter =
            new VoteInformationPresenter(this, VoteInfoRepository.getInstance(getContext()));
        mCommentAdapter = new CommentAdapter(mPresenter, mVoteInfoModel);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentVoteInfoBinding.inflate(inflater, container, false);
        mBinding.setVoteInfoModel(mVoteInfoModel);
        mBinding.layoutPollInfo.setVoteInfoModel(mVoteInfoModel);
        mBinding.layoutPostComment.setVoteInfoModel(mVoteInfoModel);
        mBinding.layoutPostComment.setPresenter((VoteInformationPresenter) mPresenter);
        mBinding.layoutComments.setFragment(this);
        mBinding.layoutComments.setVoteInfoModel(mVoteInfoModel);
        return mBinding.getRoot();
    }

    public void setCommentsView() {
        mCommentExpand.set(!mCommentExpand.get());
    }

    @Override
    public void start() {
        mCommentExpand.set(false);
    }

    @Override
    public void onPostCommentSuccess(FpollComment comment) {
        mVoteInfoModel.setItemStatus(ItemStatus.AVAILABLE);
        mCommentExpand.set(true);
        mVoteInfoModel.getVoteInfo().getPoll().getComments().add(0, comment);
        mCommentAdapter.notifyItemInserted(0);
    }

    @Override
    public void onPostCommentFailed() {
        mVoteInfoModel.setItemStatus(ItemStatus.AVAILABLE);
        Toast.makeText(getContext(), getString(R.string.error_post_comments), Toast.LENGTH_SHORT)
            .show();
    }

    @Override
    public void showEmptyError() {
        mBinding.layoutPostComment.setMsgError(getString(R.string.msg_content_error));
    }

    @Override
    public void setLoading() {
        mVoteInfoModel.setItemStatus(ItemStatus.POSTING);
    }

    public ObservableBoolean getCommentExpand() {
        return mCommentExpand;
    }

    public CommentAdapter getAdapter() {
        return mCommentAdapter;
    }
}
