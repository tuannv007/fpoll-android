package com.framgia.fpoll.ui.votemanager.vote;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ItemPollRadioBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteHolder> {
    private VoteContract.Presenter mPresenter;
    private VoteInfoModel mVoteInfoModel;
    private LayoutInflater mInflater;

    public VoteAdapter(VoteContract.Presenter presenter, VoteInfoModel voteInfoModel) {
        mVoteInfoModel = voteInfoModel;
        mPresenter = presenter;
    }

    @Override
    public VoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemPollRadioBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_poll_radio, parent, false);
        binding.setPresenter((VotePresenter) mPresenter);
        return new VoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(VoteHolder holder, int position) {
        holder.bind(mVoteInfoModel.getOptionModels().get(position));
    }

    @Override
    public int getItemCount() {
        return mVoteInfoModel.getOptionModels() == null ? 0 :
            mVoteInfoModel.getOptionModels().size();
    }

    public class VoteHolder extends RecyclerView.ViewHolder {
        private ItemPollRadioBinding mBinDingRadio;

        public VoteHolder(ItemPollRadioBinding binDing) {
            super(binDing.getRoot());
            mBinDingRadio = binDing;
        }

        private void bind(OptionModel optionModel) {
            if (optionModel == null) return;
            mBinDingRadio.setOptionModel(optionModel);
            mBinDingRadio.executePendingBindings();
        }
    }
}
