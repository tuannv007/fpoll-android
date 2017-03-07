package com.framgia.fpoll.data.ApiRestClient.APIService.pollcreationservice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 06/03/2017.
 */
public class PollResponse {
    @SerializedName("status")
    private int mStatus;
    @SerializedName("data")
    private PollItem mPollItem;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public PollItem getPollItem() {
        return mPollItem;
    }

    public void setPollItem(PollItem pollItem) {
        mPollItem = pollItem;
    }
}
