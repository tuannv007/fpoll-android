package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;

/**
 * Created by tuanbg on 2/9/17.
 */
public class User extends BaseObservable {
    private String mUsername;
    private String mEmail;
    private int mGender;
    private String mPassword;
    private String mConfirmPassword;
    private String mAvatar;
    private String mChatWorkId;
    private String mRole;

    @Bindable
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        mConfirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public String getChatWorkId() {
        return mChatWorkId;
    }

    public void setChatWorkId(String chatWorkId) {
        mChatWorkId = chatWorkId;
        notifyPropertyChanged(BR.chatWorkId);
    }

    @Bindable
    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
        notifyPropertyChanged(BR.role);
    }

    @Override
    public String toString() {
        return "User{" +
            "mUsername='" + mUsername + '\'' +
            ", mEmail='" + mEmail + '\'' +
            ", mGender=" + mGender +
            ", mPassword='" + mPassword + '\'' +
            ", mConfirmPassword='" + mConfirmPassword + '\'' +
            ", mAvatar='" + mAvatar + '\'' +
            ", mChatWorkId='" + mChatWorkId + '\'' +
            ", mRole='" + mRole + '\'' +
            '}';
    }
}
