package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedItem extends BaseObservable {
    private String mUsername;
    private String mEmail;
    private String mLink;
    private int mIdPoll;
    private String mLinkAdmin;
    @Bindable
    public String getLinkAdmin() {
        return mLinkAdmin;
    }

    public void setLinkAdmin(String linkAdmin) {
        mLinkAdmin = linkAdmin;
        notifyPropertyChanged(BR.linkAdmin);
    }

    @Bindable
    public String getUsername() {
        return mUsername;
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    @Bindable
    public String getLink() {
        return mLink;
    }

    @Bindable
    public int getIdPoll() {
        return mIdPoll;
    }

    public void setUsername(String username) {
        this.mUsername = username;
        notifyPropertyChanged(BR.username);
    }

    public void setEmail(String email) {
        this.mEmail = email;
        notifyPropertyChanged(BR.email);
    }

    public void setLink(String link) {
        this.mLink = link;
        notifyPropertyChanged(BR.link);
    }

    public void setIdPoll(int idPoll) {
        this.mIdPoll = idPoll;
        notifyPropertyChanged(BR.idPoll);
    }
}
