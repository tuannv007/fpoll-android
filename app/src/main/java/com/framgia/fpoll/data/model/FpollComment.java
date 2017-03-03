package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;

/**
 * Created by anhtv on 02/03/2017.
 */
public class FpollComment extends BaseObservable {
    private String mUserAvatar;
    private String mUserName;
    private String mContent;
    private String mRelativeTime;

    public FpollComment() {
    }

    public FpollComment(String userAvatar, String userName,
                        String content, String relativeTime) {
        mUserAvatar = userAvatar;
        mUserName = userName;
        mContent = content;
        mRelativeTime = relativeTime;
    }

    @Bindable
    public String getUserAvatar() {
        return mUserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        mUserAvatar = userAvatar;
        notifyPropertyChanged(BR.userAvatar);
    }

    @Bindable
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public String getRelativeTime() {
        return mRelativeTime;
    }

    public void setRelativeTime(String relativeTime) {
        mRelativeTime = relativeTime;
        notifyPropertyChanged(BR.relativeTime);
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
    }
}
