package com.framgia.fpoll.ui.votemanager.votedetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.databinding.ItemVoteDetailBinding;
import com.framgia.fpoll.databinding.ItemVoteDetailHeaderBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhtv on 16/03/2017.
 */
public class VoteDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_DETAIL = 1;
    private LayoutInflater mInflater;
    private Option mOption;
    private List<ParticipantVotes> mListProfiles = new ArrayList<>();

    public VoteDetailAdapter(Option option, List<ParticipantVotes> listProfiles) {
        mOption = option;
        mListProfiles = listProfiles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        return viewType == TYPE_HEADER ?
            new HeaderViewHolder((ItemVoteDetailHeaderBinding) DataBindingUtil
                .inflate(mInflater, R.layout.item_vote_detail_header, parent, false)) :
            new DetailViewHolder((ItemVoteDetailBinding) DataBindingUtil
                .inflate(mInflater, R.layout.item_vote_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            ((HeaderViewHolder) holder).bind(mOption);
            return;
        }
        ((DetailViewHolder) holder).bind(mListProfiles.get(position));
    }

    @Override
    public int getItemCount() {
        return mListProfiles == null ? 0 : mListProfiles.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_DETAIL;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ItemVoteDetailHeaderBinding mBinding;

        public HeaderViewHolder(ItemVoteDetailHeaderBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Option option) {
            if (option == null) return;
            mBinding.setOption(option);
            mBinding.executePendingBindings();
        }
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        private ItemVoteDetailBinding mBinding;

        public DetailViewHolder(ItemVoteDetailBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(ParticipantVotes participantVotes) {
            if (participantVotes == null) return;
            mBinding.setParticipantVote(participantVotes);
            mBinding.executePendingBindings();
        }
    }
}
