package com.framgia.fpoll.ui.history.pollhistory;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;
import com.framgia.fpoll.databinding.ItemPollHistoryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/16/2017.
 * <></>
 */
public class PollHistoryAdapter extends RecyclerView.Adapter<PollHistoryAdapter.PollHistoryHolder> {
    private LayoutInflater mInflater;
    private List<PollHistoryItem> mListPollHistory = new ArrayList<>();
    private PollHistoryType mHistoryType;
    private PollHistoryContract.Presenter mPresenter;

    public PollHistoryAdapter(List<PollHistoryItem> pollHistories, PollHistoryType pollHistoryType,
                              PollHistoryContract.Presenter presenter) {
        mHistoryType = pollHistoryType;
        mListPollHistory.addAll(pollHistories);
        mPresenter = presenter;
    }

    public void update(List<PollHistoryItem> pollHistories) {
        mListPollHistory.clear();
        mListPollHistory.addAll(pollHistories);
        notifyDataSetChanged();
    }

    @Override
    public PollHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemPollHistoryBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_poll_history, parent, false);
        binding.setHandler(new PollHistoryHandler(mPresenter));
        switch (mHistoryType) {
            case CLOSE:
                binding.setTitle(parent.getContext().getString(R.string.title_re_open));
                binding.setIcon(parent.getContext().getResources().getDrawable(R.drawable.ic_open));
                break;
            case INITIATE:
            case PARTICIPATE:
            default:
                binding.setTitle(parent.getContext().getString(R.string.msg_link));
                binding.setIcon(
                    parent.getContext().getResources().getDrawable(R.drawable.ic_link_white));
                break;
        }
        return new PollHistoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(PollHistoryHolder holder, int position) {
        PollHistoryItem item = mListPollHistory.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListPollHistory == null ? 0 : mListPollHistory.size();
    }

    public class PollHistoryHolder extends RecyclerView.ViewHolder {
        private ItemPollHistoryBinding mBinding;

        public PollHistoryHolder(ItemPollHistoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(PollHistoryItem item) {
            mBinding.setPollHistory(item);
            mBinding.executePendingBindings();
        }
    }
}
