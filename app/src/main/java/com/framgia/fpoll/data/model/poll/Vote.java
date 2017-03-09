package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 08/03/2017.
 */
public class Vote extends BaseObservable {
    @SerializedName("id")
    private String mId;
    @SerializedName("vote_id")
    private String mVoteId;
    @SerializedName("option_id")
    private String mOptionId;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getVoteId() {
        return mVoteId;
    }

    public void setVoteId(String voteId) {
        mVoteId = voteId;
        notifyPropertyChanged(BR.voteId);
    }

    @Bindable
    public String getOptionId() {
        return mOptionId;
    }

    public void setOptionId(String optionId) {
        mOptionId = optionId;
        notifyPropertyChanged(BR.optionId);
    }

    @Bindable
    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
        notifyPropertyChanged(BR.createdTime);
    }

    @Bindable
    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        mUpdatedTime = updatedTime;
        notifyPropertyChanged(BR.updatedTime);
    }
}
