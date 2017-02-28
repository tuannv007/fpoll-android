package com.framgia.fpoll.ui.pollcreation.option;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.OptionItem;
import com.framgia.fpoll.databinding.ItemPageOptionBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 3/1/2017.
 * <></>
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionHolder> {
    private LayoutInflater mInflater;
    private List<OptionItem> mListOption = new ArrayList<>();
    private OptionPollContract.Presenter mPresenter;

    public OptionAdapter(OptionPollContract.Presenter presenter, List<OptionItem> optionItems) {
        mPresenter = presenter;
        mListOption.addAll(optionItems);
        notifyDataSetChanged();
    }

    public void update(List<OptionItem> optionItems) {
        mListOption.clear();
        mListOption.addAll(optionItems);
        notifyDataSetChanged();
    }

    @Override
    public OptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemPageOptionBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_page_option, parent, false);
        binding.setHandler(new OptionHandler(mPresenter));
        return new OptionHolder(binding);
    }

    @Override
    public void onBindViewHolder(OptionHolder holder, int position) {
        OptionItem optionItem = mListOption.get(position);
        if (optionItem != null) holder.bind(optionItem, position);
    }

    @Override
    public int getItemCount() {
        return mListOption == null ? 0 : mListOption.size();
    }

    public class OptionHolder extends RecyclerView.ViewHolder {
        private ItemPageOptionBinding mBinding;

        public OptionHolder(ItemPageOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(OptionItem optionItem, int position) {
            mBinding.setOption(optionItem);
            mBinding.setPosition(position);
            mBinding.executePendingBindings();
        }
    }
}
