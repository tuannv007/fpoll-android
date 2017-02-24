package com.framgia.fpoll.ui.resultvote;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.databinding.ItemResultVoteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class ResultVoteAdapter extends RecyclerView.Adapter<ResultVoteAdapter.ResultVoteHolder> {
    private List<ResultItem> mResultItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public ResultVoteAdapter(List<ResultItem> resultItems) {
        mResultItems = resultItems;
    }

    @Override
    public ResultVoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemResultVoteBinding binding = DataBindingUtil.inflate(mInflater, R.layout
            .item_result_vote, parent, false);
        return new ResultVoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultVoteHolder holder, int position) {
        ResultItem item = mResultItems.get(position);
        if (item != null) holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mResultItems == null ? 0 : mResultItems.size();
    }

    public class ResultVoteHolder extends RecyclerView.ViewHolder {
        private ItemResultVoteBinding mBinDing;

        public ResultVoteHolder(ItemResultVoteBinding binDing) {
            super(binDing.getRoot());
            mBinDing = binDing;
        }

        private void bind(ResultItem item) {
            mBinDing.setItem(item);
            mBinDing.executePendingBindings();
        }
    }
}
