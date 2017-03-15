package com.framgia.fpoll.ui.votemanager.vote;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ItemVoteMultipleBinding;
import com.framgia.fpoll.databinding.ItemVoteSingleBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private VoteContract.Presenter mPresenter;
    private VoteInfoModel mVoteInfoModel;
    private LayoutInflater mInflater;

    public VoteAdapter(VoteContract.Presenter presenter, VoteInfoModel voteInfoModel) {
        mVoteInfoModel = voteInfoModel;
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VoteType.TYPE_MULTIPLE.getValue()) {
            ItemVoteMultipleBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_vote_multiple, parent, false);
            binding.setPresenter((VotePresenter) mPresenter);
            return new VoteMultipleBoxHolder(binding);
        } else {
            ItemVoteSingleBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_vote_single, parent, false);
            binding.setPresenter((VotePresenter) mPresenter);
            return new VoteSingleHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VoteType.TYPE_MULTIPLE.getValue()) {
            ((VoteMultipleBoxHolder) holder).bind(mVoteInfoModel.getOptionModels().get(position));
        } else {
            ((VoteSingleHolder) holder).bind(mVoteInfoModel.getOptionModels().get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mVoteInfoModel.getOptionModels() == null ? 0 :
            mVoteInfoModel.getOptionModels().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mVoteInfoModel.getVoteInfo() == null) return super.getItemViewType(position);
        if (mVoteInfoModel.getVoteInfo().getPoll().isMultiple()) {
            return VoteType.TYPE_MULTIPLE.getValue();
        } else {
            return VoteType.TYPE_SINGLE.getValue();
        }
    }

    public class VoteSingleHolder extends RecyclerView.ViewHolder {
        private ItemVoteSingleBinding mBindingSingle;

        public VoteSingleHolder(ItemVoteSingleBinding binDing) {
            super(binDing.getRoot());
            mBindingSingle = binDing;
        }

        private void bind(OptionModel optionModel) {
            if (optionModel == null) return;
            mBindingSingle.setOptionModel(optionModel);
            mBindingSingle.executePendingBindings();
        }
    }

    public class VoteMultipleBoxHolder extends RecyclerView.ViewHolder {
        private ItemVoteMultipleBinding mBindingMultiple;

        public VoteMultipleBoxHolder(ItemVoteMultipleBinding binDing) {
            super(binDing.getRoot());
            mBindingMultiple = binDing;
        }

        private void bind(OptionModel optionModel) {
            if (optionModel == null) return;
            mBindingMultiple.setOptionModel(optionModel);
            mBindingMultiple.executePendingBindings();
        }
    }
}
