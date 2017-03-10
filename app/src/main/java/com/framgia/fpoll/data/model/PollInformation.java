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
    private int mMultiple;
    private String mDescription;
    private String mDateClose;
    private String mLocation;

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

    @Bindable
    public int getMultiple() {
        return mMultiple;
    }

    public void setMultiple(int multiple) {
        mMultiple = multiple;
        notifyPropertyChanged(BR.multiple);
    }

    @Bindable
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getDateClose() {
        return mDateClose;
    }

    public void setDateClose(String dateClose) {
        mDateClose = dateClose;
        notifyPropertyChanged(BR.dateClose);
    }

    @Bindable
    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
        notifyPropertyChanged(BR.location);
    }
}
