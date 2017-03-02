package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;

/**
 * Created by framgia on 02/03/2017.
 */
public class PollInformation extends BaseObservable {
    private String mUserName;
    private String mEmail;
    private String mPollTitle;

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
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
    public String getPollTitle() {
        return mPollTitle;
    }

    public void setPollTitle(String pollTitle) {
        mPollTitle = pollTitle;
        notifyPropertyChanged(BR.pollTitle);
    }
}
