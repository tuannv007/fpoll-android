package com.framgia.fpoll.ui.polledition.editoption;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.ItemEditOptionBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionAdapter extends RecyclerView.Adapter<EditOptionAdapter.EditOptionHolder> {
    private LayoutInflater mInflater;
    private List<Option> mListOption = new ArrayList<>();
    private EditOptionContract.Presenter mPresenter;

    public EditOptionAdapter(EditOptionContract.Presenter presenter, List<Option> optionItems) {
        mPresenter = presenter;
        mListOption.addAll(optionItems);
        notifyDataSetChanged();
    }

    public void update(List<Option> optionItems) {
        mListOption.clear();
        mListOption.addAll(optionItems);
        notifyDataSetChanged();
    }

    @Override
    public EditOptionAdapter.EditOptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemEditOptionBinding binding = ItemEditOptionBinding.inflate(mInflater, parent, false);
        binding.setHandler(new EditOptionHandle(mPresenter));
        return new EditOptionAdapter.EditOptionHolder(binding);
    }

    @Override
    public void onBindViewHolder(EditOptionAdapter.EditOptionHolder holder, int position) {
        Option option = mListOption.get(position);
        if (option != null) holder.bind(option, position);
    }

    @Override
    public int getItemCount() {
        return mListOption == null ? 0 : mListOption.size();
    }

    public class EditOptionHolder extends RecyclerView.ViewHolder {
        private ItemEditOptionBinding mBinding;

        public EditOptionHolder(ItemEditOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(Option option, int position) {
            mBinding.setOption(option);
            mBinding.setPosition(position);
            mBinding.executePendingBindings();
        }
    }
}
