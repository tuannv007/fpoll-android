package com.framgia.fpoll.ui.pollmanage.history;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.databinding.ItemViewHistoryActivityBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/28/17.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<FpollComment> mListItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public HistoryAdapter(List<FpollComment> listItems) {
        this.mListItems = listItems;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemViewHistoryActivityBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_view_history_activity, parent,
                        false);
        return new HistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        FpollComment item = mListItems.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListItems != null ? mListItems.size() : 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private ItemViewHistoryActivityBinding mBinding;

        public HistoryViewHolder(ItemViewHistoryActivityBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(FpollComment item) {
            mBinding.setItem(item);
            mBinding.executePendingBindings();
        }
    }
}
