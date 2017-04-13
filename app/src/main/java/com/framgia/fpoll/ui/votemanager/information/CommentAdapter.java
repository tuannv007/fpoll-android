package com.framgia.fpoll.ui.votemanager.information;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.databinding.ItemPollCommentBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by anhtv on 27/02/2017.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private VoteInformationContract.Presenter mPresenter;
    private LayoutInflater mInflater;
    private VoteInfoModel mVoteInfoModel;

    public CommentAdapter(VoteInformationContract.Presenter presenter,
            VoteInfoModel voteInfoModel) {
        mPresenter = presenter;
        mVoteInfoModel = voteInfoModel;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        return new CommentViewHolder(mInflater.inflate(R.layout.item_poll_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.bind(mVoteInfoModel.getVoteInfo().getPoll().getComments().get(position));
    }

    @Override
    public int getItemCount() {
        return mVoteInfoModel.getVoteInfo().getPoll().getComments() == null ? 0
                : mVoteInfoModel.getVoteInfo().getPoll().getComments().size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private ItemPollCommentBinding mBinding;

        public CommentViewHolder(View itemView) {
            super(itemView);
            mBinding = ItemPollCommentBinding.bind(itemView);
        }

        public void bind(FpollComment fpollComment) {
            if (fpollComment == null) return;
            mBinding.setComment(fpollComment);
            mBinding.executePendingBindings();
        }
    }
}
