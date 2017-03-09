package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.voteinfo.VoteInfo;

import java.io.Serializable;

/**
 * Created by anhtv on 09/03/2017.
 */
public class VoteInfoModel extends BaseObservable implements Serializable {
    private VoteInfo mVoteInfo;
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
}
