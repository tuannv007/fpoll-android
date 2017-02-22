package com.framgia.fpoll.ui.introductapp;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.databinding.ItemIntroduceAppBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceAdapter extends RecyclerView.Adapter<IntroduceAdapter.IntroduceViewHolder> {
    private List<IntroduceItem> mListItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public IntroduceAdapter(List<IntroduceItem> listItems) {
        mListItems.addAll(listItems);
    }

    @Override
    public IntroduceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemIntroduceAppBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_introduce_app, parent, false);
        return new IntroduceAdapter.IntroduceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(IntroduceViewHolder holder, int position) {
        IntroduceItem item = mListItems.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListItems == null ? 0 : mListItems.size();
    }

    public class IntroduceViewHolder extends RecyclerView.ViewHolder {
        private ItemIntroduceAppBinding mBinding;

        public IntroduceViewHolder(ItemIntroduceAppBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(IntroduceItem item) {
            mBinding.setItem(item);
            mBinding.executePendingBindings();
        }
    }
}
