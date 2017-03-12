package com.framgia.fpoll.ui.pollmanage.result;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.databinding.ItemResultVoteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVoteAdapter extends RecyclerView.Adapter<ResultVoteAdapter.ResultVoteHolder> {
    private List<ResultVoteItem.Result> mListItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public ResultVoteAdapter(List<ResultVoteItem.Result> listItems) {
        mListItems = listItems;
    }

    @Override
    public ResultVoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemResultVoteBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_result_vote, parent, false);
        return new ResultVoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultVoteHolder holder, int position) {
        ResultVoteItem.Result item = mListItems.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mListItems != null ? mListItems.size() : 0;
    }

    public class ResultVoteHolder extends RecyclerView.ViewHolder {
        private ItemResultVoteBinding mBinding;

        public ResultVoteHolder(ItemResultVoteBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(ResultVoteItem.Result item) {
            mBinding.setItem(item);
            mBinding.executePendingBindings();
        }
    }
}
