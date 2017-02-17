package com.framgia.fpoll.data.model;

/**
 * Created by tuanbg on 2/9/17.
 */
public class User {
    private String mUsername;
    private String mEmail;
    private int mGender;
    private String mPassword;
    private String mAvatar;
    private String mChatWorkId;
    private String mRole;

    public User(String username, String email, int gender, String password, String avatar,
                String chatWorkId, String role) {
        mUsername = username;
        mEmail = email;
        mGender = gender;
        mPassword = password;
        mAvatar = avatar;
        mChatWorkId = chatWorkId;
        mRole = role;
    }

    public User(String username, String email, int gender, String password, String avatar) {
        mUsername = username;
        mEmail = email;
        mGender = gender;
        mPassword = password;
        mAvatar = avatar;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public int getGender() {
        return mGender;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public String getChatWorkId() {
        return mChatWorkId;
    }

    public String getRole() {
        return mRole;
    }
}
