package com.framgia.fpoll.ui.votemanager.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.databinding.ItemVoteDetailBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 4/4/2017.
 * <></>
 */
public class VotingDetailAdapter
        extends RecyclerView.Adapter<VotingDetailAdapter.VotingDetailHolder> {
    private List<VoteDetail.Result> mResultList = new ArrayList<>();
    private LayoutInflater mInflater;

    public void update(List<VoteDetail.Result> results) {
        mResultList.clear();
        mResultList.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public VotingDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemVoteDetailBinding binding = ItemVoteDetailBinding.inflate(mInflater, parent, false);
        return new VotingDetailHolder(binding);
    }

    @Override
    public void onBindViewHolder(VotingDetailHolder holder, int position) {
        VoteDetail.Result result = mResultList.get(position);
        if (result != null) holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return mResultList == null ? 0 : mResultList.size();
    }

    public class VotingDetailHolder extends RecyclerView.ViewHolder {
        private ItemVoteDetailBinding mBinding;

        public VotingDetailHolder(ItemVoteDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(VoteDetail.Result result) {
            mBinding.setResult(result);
            mBinding.executePendingBindings();
        }
    }
}
