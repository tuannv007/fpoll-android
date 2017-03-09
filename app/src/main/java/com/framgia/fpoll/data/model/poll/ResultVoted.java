package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 07/03/2017.
 */
public class ResultVoted extends BaseObservable {
    @SerializedName("option_id")
    private int mOptionId;
    @SerializedName("count_vote")
    private int mCountVote;

    @Bindable
    public int getCountVote() {
        return mCountVote;
    }

    public void setCountVote(int countVote) {
        mCountVote = countVote;
        notifyPropertyChanged(BR.countVote);
    }

    @Bindable
    public int getOptionId() {
        return mOptionId;
    }

    public void setOptionId(int optionId) {
        mOptionId = optionId;
        notifyPropertyChanged(BR.optionId);
    }
}
