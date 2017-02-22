package com.framgia.fpoll.ui.vote;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.VoteItem;
import com.framgia.fpoll.databinding.ItemPollTypeListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteHolder> {
    private List<VoteItem> mVoteItems;
    private LayoutInflater mInflater;

    public VoteAdapter(List<VoteItem> voteItems) {
        mVoteItems = voteItems;
    }

    @Override
    public VoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemPollTypeListBinding binding = DataBindingUtil.inflate(mInflater, R.layout
            .item_poll_type_list, parent, false);
        return new VoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(VoteHolder holder, int position) {
        VoteItem voteItem = mVoteItems.get(position);
        if (voteItem != null) holder.bind(voteItem);
    }

    @Override
    public int getItemCount() {
        return mVoteItems == null ? 0 : mVoteItems.size();
    }

    public class VoteHolder extends RecyclerView.ViewHolder {
        private ItemPollTypeListBinding mBinDing;

        public VoteHolder(ItemPollTypeListBinding binDing) {
            super(binDing.getRoot());
            mBinDing = binDing;
        }

        private void bind(VoteItem item) {
            mBinDing.setItemvote(item);
            mBinDing.executePendingBindings();
        }
    }
}
