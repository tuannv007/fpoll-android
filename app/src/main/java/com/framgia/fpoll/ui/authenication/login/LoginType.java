package com.framgia.fpoll.ui.authenication.login;

/**
 * Created by Nhahv0902 on 2/20/2017.
 * <></>
 */
public enum LoginType {
    NORMAL("normal"), FACEBOOK("facebook"), GOOGLE("google"), TWITTER("twitter");
    private final String mProvider;

    LoginType(String type) {
        mProvider = type;
    }

    public String getProvider() {
        return mProvider;
    }
}
