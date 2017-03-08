package com.framgia.fpoll.data.model.voteinfo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 08/03/2017.
 */
public class Vote {
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

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getVoteId() {
        return mVoteId;
    }

    public void setVoteId(String voteId) {
        mVoteId = voteId;
    }

    public String getOptionId() {
        return mOptionId;
    }

    public void setOptionId(String optionId) {
        mOptionId = optionId;
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
