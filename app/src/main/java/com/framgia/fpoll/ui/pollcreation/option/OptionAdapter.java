package com.framgia.fpoll.ui.pollcreation.option;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.ItemPageOptionBinding;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/1/2017.
 * <></>
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionHolder> {
    private LayoutInflater mInflater;
    private List<Option> mListOption;
    private OptionPollContract.Presenter mPresenter;

    public OptionAdapter(OptionPollContract.Presenter presenter, List<Option> optionItems) {
        mPresenter = presenter;
        mListOption = optionItems;
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
        Option option = mListOption.get(position);
        if (option != null) holder.bind(option);
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

        private void bind(Option option) {
            mBinding.setOption(option);
            mBinding.setPosition(getAdapterPosition());
            mBinding.executePendingBindings();
        }
    }
}
