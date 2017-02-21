package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by Nhahv0902 on 2/16/2017.
 * <></>
 */
public class PollHistoryItem extends BaseObservable {
    private String mSubject;
    private int mNumberParticipant;
    private String mLastActivity;
    private String mLinkViewAdmin;

    public PollHistoryItem(String subject, int numberParticipant, String lastActivity,
                           String linkViewAdmin) {
        mSubject = subject;
        mNumberParticipant = numberParticipant;
        mLastActivity = lastActivity;
        mLinkViewAdmin = linkViewAdmin;
    }

    @Bindable
    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
        notifyPropertyChanged(BR.subject);
    }

    @Bindable
    public int getNumberParticipant() {
        return mNumberParticipant;
    }

    public void setNumberParticipant(int numberParticipant) {
        mNumberParticipant = numberParticipant;
        notifyPropertyChanged(BR.numberParticipant);
    }

    @Bindable
    public String getLastActivity() {
        return mLastActivity;
    }

    public void setLastActivity(String lastActivity) {
        mLastActivity = lastActivity;
        notifyPropertyChanged(BR.lastActivity);
    }

    @Bindable
    public String getLinkViewAdmin() {
        return mLinkViewAdmin;
    }

    public void setLinkViewAdmin(String linkViewAdmin) {
        mLinkViewAdmin = linkViewAdmin;
        notifyPropertyChanged(BR.linkViewAdmin);
    }
}
