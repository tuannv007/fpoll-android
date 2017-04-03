package com.framgia.fpoll.ui.votemanager.vote;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.ItemVoteSingleBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteSingleHolder> {
    private VoteContract.Presenter mPresenter;
    private VoteInfoModel mVoteInfoModel;
    private LayoutInflater mInflater;
    private boolean mIsMultiple;

    public VoteAdapter(VoteContract.Presenter presenter, VoteInfoModel voteInfoModel) {
        mVoteInfoModel = voteInfoModel;
        mPresenter = presenter;
        mIsMultiple = mVoteInfoModel.getVoteInfo().getPoll().isMultiple();
    }

    @Override
    public VoteAdapter.VoteSingleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemVoteSingleBinding binding = ItemVoteSingleBinding.inflate(mInflater, parent, false);
        binding.setPresenter((VotePresenter) mPresenter);
        binding.setIsMultiple(mIsMultiple);
        return new VoteSingleHolder(binding);
    }

    @Override
    public void onBindViewHolder(VoteSingleHolder holder, int position) {
        Option option = mVoteInfoModel.getOptionModels().get(position);
        if (option != null) holder.bind(option);
    }

    @Override
    public int getItemCount() {
        return mVoteInfoModel.getOptionModels() == null ? 0 :
            mVoteInfoModel.getOptionModels().size();
    }

    public class VoteSingleHolder extends RecyclerView.ViewHolder {
        private ItemVoteSingleBinding mBindingSingle;

        public VoteSingleHolder(ItemVoteSingleBinding binDing) {
            super(binDing.getRoot());
            mBindingSingle = binDing;
        }

        private void bind(Option option) {
            mBindingSingle.setOption(option);
            mBindingSingle.executePendingBindings();
        }
    }
}
