package com.framgia.fpoll.data.model.poll;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 10/03/2017.
 */
public class PollLink {
    @SerializedName("token")
    private String mToken;
    @SerializedName("link_admin")
    private Boolean mLinkAdmin;
    @SerializedName("poll_id")
    private int mPollId;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public Boolean getLinkAdmin() {
        return mLinkAdmin;
    }

    public void setLinkAdmin(Boolean linkAdmin) {
        mLinkAdmin = linkAdmin;
    }

    public int getPollId() {
        return mPollId;
    }

    public void setPollId(int pollId) {
        mPollId = pollId;
    }
}
