package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Setting extends BaseObservable {
    @SerializedName("id")
    private int mId;
    @SerializedName("poll_id")
    private int mPollId;
    @SerializedName("key")
    private int mKey;
    @SerializedName("value")
    private String mValue;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getPollId() {
        return mPollId;
    }

    public void setPollId(int pollId) {
        mPollId = pollId;
        notifyPropertyChanged(BR.pollId);
    }

    @Bindable
    public int getKey() {
        return mKey;
    }

    public void setKey(int key) {
        mKey = key;
        notifyPropertyChanged(BR.key);
    }

    @Bindable
    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
        notifyPropertyChanged(BR.value);
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
