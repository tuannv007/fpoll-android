package com.framgia.fpoll.data.model.authorization;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nhahv0902 on 3/3/2017.
 * <></>
 */
public class SocialData {
    @SerializedName("user")
    private User mUser;
    @SerializedName("token")
    private String mToken;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }
}
