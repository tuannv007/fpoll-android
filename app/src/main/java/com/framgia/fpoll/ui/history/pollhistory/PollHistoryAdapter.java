package com.framgia.fpoll.ui.history.pollhistory;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.databinding.ItemPollHistoryBinding;
import com.framgia.fpoll.databinding.NoPollItemBinding;
import com.framgia.fpoll.ui.history.PollHistoryType;
import com.framgia.fpoll.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/16/2017.
 * <></>
 */
public class PollHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_EMPTY = 1;
    private LayoutInflater mInflater;
    private List<HistoryPoll> mListPollHistory = new ArrayList<>();
    private PollHistoryType mHistoryType;
    private PollHistoryContract.Presenter mPresenter;
    private Context mContext;

    public PollHistoryAdapter(Context context, List<HistoryPoll> pollHistories, PollHistoryType
        pollHistoryType,
                              PollHistoryContract.Presenter presenter) {
        mContext = context;
        mHistoryType = pollHistoryType;
        mListPollHistory.addAll(pollHistories);
        mPresenter = presenter;
    }

    public void update(List<HistoryPoll> pollHistories) {
        mListPollHistory.clear();
        mListPollHistory.addAll(pollHistories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mListPollHistory == null || mListPollHistory.size() == 0) return VIEW_TYPE_EMPTY;
        return VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_EMPTY) {
            NoPollItemBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.no_poll_item, parent, false);
            return new NoItemHolder(binding);
        }
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PollHistoryHolder) {
            PollHistoryHolder pollHistoryHolder = (PollHistoryHolder) holder;
            HistoryPoll item = mListPollHistory.get(position);
            if (item != null) pollHistoryHolder.bind(item);
            return;
        }
        NoItemHolder noItemHolder = (NoItemHolder) holder;
        noItemHolder.bind();
    }

    @Override
    public int getItemCount() {
        return (mListPollHistory == null || mListPollHistory.size() == 0) ? 1 :
            mListPollHistory.size();
    }

    public class PollHistoryHolder extends RecyclerView.ViewHolder {
        private ItemPollHistoryBinding mBinding;

        public PollHistoryHolder(ItemPollHistoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(HistoryPoll item) {
            mBinding.setPollHistory(item);
            mBinding.executePendingBindings();
        }
    }

    public class NoItemHolder extends RecyclerView.ViewHolder {
        private NoPollItemBinding mBinding;

        public NoItemHolder(NoPollItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind() {
            String message = SharePreferenceUtil.getIntances(mContext).isLogin() ?
                mContext.getString(R.string.no_item) :
                mContext.getString(R.string.no_item_need_login);
            mBinding.setMessage(message);
            mBinding.executePendingBindings();
        }
    }
}
