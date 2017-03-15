package com.framgia.fpoll.ui.votemanager.result;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.ItemVoteResultBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by anhtv on 15/03/2017.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private VoteInfoModel mVoteInfoModel;
    private LinkVoteResultPresenter mPresenter;
    private LayoutInflater mInflater;

    public ResultAdapter(VoteInfoModel voteInfoModel, LinkVoteResultPresenter presenter) {
        mVoteInfoModel = voteInfoModel;
        mPresenter = presenter;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemVoteResultBinding binding = DataBindingUtil
            .inflate(mInflater, R.layout.item_vote_result, parent, false);
        binding.setPresenter(mPresenter);
        return new ResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.bind(mVoteInfoModel.getVoteInfo().getPoll().getOptions().get(position));
    }

    @Override
    public int getItemCount() {
        return (mVoteInfoModel == null || mVoteInfoModel.getVoteInfo() == null) ? 0 :
            mVoteInfoModel.getVoteInfo().getPoll().getOptions().size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private ItemVoteResultBinding mBinding;

        public ResultViewHolder(ItemVoteResultBinding binDing) {
            super(binDing.getRoot());
            mBinding = binDing;
        }

        private void bind(Option option) {
            if (option == null) return;
            mBinding.setOption(option);
            mBinding.executePendingBindings();
        }
    }
}
