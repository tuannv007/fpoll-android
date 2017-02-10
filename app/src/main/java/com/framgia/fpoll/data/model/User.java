package com.framgia.fpoll.data.model;

/**
 * Created by tuanbg on 2/9/17.
 */
public class User {
    private String mUsername;
    private String mEmail;
    private String mGender;
    private String mPassword;
    private String mLinkImage;

    public User(String username, String email, String gender, String password,
                String rePassword, String linkImage) {
        mUsername = username;
        mEmail = email;
        mGender = gender;
        mPassword = password;
        mLinkImage = linkImage;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getGender() {
        return mGender;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getLinkImage() {
        return mLinkImage;
    }
}
