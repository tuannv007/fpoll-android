package com.framgia.fpoll.ui.pollmanage.information.viewoption;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.ItemViewOptionBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/9/17.
 */
public class PollInfoAdapter
    extends RecyclerView.Adapter<PollInfoAdapter.PollOptionViewHolder> {
    private List<Option> mListItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public PollInfoAdapter(List<Option> listItems) {
        mListItems = listItems;
    }

    @Override
    public PollOptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemViewOptionBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_view_option, parent, false);
        return new PollOptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PollOptionViewHolder holder, int position) {
        Option item = mListItems.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListItems != null ? mListItems.size() : 0;
    }

    public class PollOptionViewHolder extends RecyclerView.ViewHolder {
        private ItemViewOptionBinding mBinding;

        public PollOptionViewHolder(ItemViewOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Option item) {
            mBinding.setItem(item);
            mBinding.executePendingBindings();
        }
    }
}
