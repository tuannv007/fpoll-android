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
    private String mIdPoll;

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
    public String getIdPoll() {
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

    public void setIdPoll(String idPoll) {
        this.mIdPoll = idPoll;
        notifyPropertyChanged(BR.idPoll);
    }
}
