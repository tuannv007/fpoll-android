package com.framgia.fpoll.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class LoginNormalData {
    @SerializedName("token_type")
    private String mTokenType;
    @SerializedName("expires_in")
    private int mExpiresIn;
    @SerializedName("access_token")
    private String mAccessToken;
    @SerializedName("refresh_token")
    private String mRefreshToken;

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        mExpiresIn = expiresIn;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }
}
