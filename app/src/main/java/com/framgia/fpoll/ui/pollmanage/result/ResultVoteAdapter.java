package com.framgia.fpoll.ui.pollmanage.result;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.databinding.ItemResultVoteBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVoteAdapter extends RecyclerView.Adapter<ResultVoteAdapter.ResultVoteHolder> {
    private List<ResultVoteItem.Result> mResultList = new ArrayList<>();
    private LayoutInflater mInflater;

    public void update(List<ResultVoteItem.Result> results) {
        mResultList.clear();
        mResultList.addAll(results);
        notifyDataSetChanged();
    }

    @Override
    public ResultVoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemResultVoteBinding binding = ItemResultVoteBinding.inflate(mInflater, parent, false);
        return new ResultVoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultVoteHolder holder, int position) {
        ResultVoteItem.Result item = mResultList.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mResultList != null ? mResultList.size() : 0;
    }

    public class ResultVoteHolder extends RecyclerView.ViewHolder {
        private ItemResultVoteBinding mBinding;

        public ResultVoteHolder(ItemResultVoteBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(ResultVoteItem.Result item) {
            mBinding.setResult(item);
            mBinding.executePendingBindings();
        }
    }
}
