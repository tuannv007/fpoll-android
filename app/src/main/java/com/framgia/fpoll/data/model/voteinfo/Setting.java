package com.framgia.fpoll.data.model.voteinfo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Setting {
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getPollId() {
        return mPollId;
    }

    public void setPollId(int pollId) {
        mPollId = pollId;
    }

    public int getKey() {
        return mKey;
    }

    public void setKey(int key) {
        mKey = key;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
    }

    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        mUpdatedTime = updatedTime;
    }
}
