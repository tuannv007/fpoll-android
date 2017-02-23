package com.framgia.fpoll.ui.votemanager.vote;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.VoteItem;
import com.framgia.fpoll.databinding.ItemPollRadioBinding;

import java.util.List;

import static com.framgia.fpoll.ui.votemanager.vote.TypeItemVote.SINGLE_CHOISE;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteHolder> {
    private VoteContract.Presenter mPresenter;
    private List<VoteItem> mVoteItems;
    private LayoutInflater mInflater;
    private TypeItemVote mTypeItem;

    public VoteAdapter(List<VoteItem> voteItems, VoteContract.Presenter presenter,
                       TypeItemVote type) {
        mVoteItems = voteItems;
        mPresenter = presenter;
        mTypeItem = type;
    }

    @Override
    public VoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) mInflater = LayoutInflater.from(parent.getContext());
        ItemPollRadioBinding binding =
            DataBindingUtil.inflate(mInflater, R.layout.item_poll_radio, parent, false);
        binding.setIsSingleChoise(mTypeItem == SINGLE_CHOISE);
        binding.setHandler(new VoteHandler(mPresenter));
        return new VoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(VoteHolder holder, int position) {
        VoteItem voteItem = mVoteItems.get(position);
        if (voteItem == null) return;
        holder.bind(voteItem);
    }

    @Override
    public int getItemCount() {
        return mVoteItems == null ? 0 : mVoteItems.size();
    }

    public void updateVoteItem(VoteItem voteItem) {
        if (mTypeItem == SINGLE_CHOISE) {
            for (VoteItem item : mVoteItems) {
                if (item.getContentVote().equals(voteItem.getContentVote())) {
                    item.setCheckAnswer(true);
                } else item.setCheckAnswer(false);
            }
        } else voteItem.setCheckAnswer(!voteItem.isCheckAnswer());
    }

    public class VoteHolder extends RecyclerView.ViewHolder {
        private ItemPollRadioBinding mBinDingRadio;

        public VoteHolder(ItemPollRadioBinding binDing) {
            super(binDing.getRoot());
            mBinDingRadio = binDing;
        }

        private void bind(VoteItem item) {
            mBinDingRadio.setVoteItem(item);
            mBinDingRadio.executePendingBindings();
        }
    }
}
