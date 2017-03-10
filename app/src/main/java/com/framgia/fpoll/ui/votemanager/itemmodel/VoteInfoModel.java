package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.VoteInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anhtv on 09/03/2017.
 */
public class VoteInfoModel extends BaseObservable implements Serializable {
    private VoteInfo mVoteInfo;
    private List<OptionModel> mOptionModels;
    private ItemStatus mItemStatus = ItemStatus.NOT_AVAILABLE;

    @Bindable
    public ItemStatus getItemStatus() {
        return mItemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        mItemStatus = itemStatus;
        notifyPropertyChanged(BR.itemStatus);
    }

    @Bindable
    public VoteInfo getVoteInfo() {
        return mVoteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        mVoteInfo = voteInfo;
        notifyPropertyChanged(BR.voteInfo);
    }

    @Bindable
    public List<OptionModel> getOptionModels() {
        return mOptionModels;
    }

    public void setOptionModels(List<OptionModel> optionModels) {
        mOptionModels = optionModels;
        notifyPropertyChanged(BR.optionModel);
    }
}
