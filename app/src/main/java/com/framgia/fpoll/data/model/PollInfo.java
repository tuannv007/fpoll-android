package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.authorization.User;

import java.util.List;

/**
 * Created by anhtv on 02/03/2017.
 */
public class PollInfo extends BaseObservable {
    private User mUser;
    private String mCreatedTime;
    private String mPollTitle;
    private String mExpiredTime;
    private List<FpollComment> mComments;
    private int mTotalComments;
    private int mTotalCommentUsers;
    private String mLinkPoll;

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
        notifyPropertyChanged(BR.createdTime);
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
    public String getExpiredTime() {
        return mExpiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        mExpiredTime = expiredTime;
        notifyPropertyChanged(BR.expiredTime);
    }

    @Bindable
    public List<FpollComment> getComments() {
        return mComments;
    }

    public void setComments(List<FpollComment> comments) {
        mComments = comments;
        notifyPropertyChanged(BR.comments);
    }

    @Bindable
    public int getTotalComments() {
        return mTotalComments;
    }

    public void setTotalComments(int totalComments) {
        mTotalComments = totalComments;
        notifyPropertyChanged(BR.totalComments);
    }

    @Bindable
    public int getTotalCommentUsers() {
        return mTotalCommentUsers;
    }

    public void setTotalCommentUsers(int totalUserComments) {
        mTotalCommentUsers = totalUserComments;
        notifyPropertyChanged(BR.totalCommentUsers);
    }

    @Bindable
    public String getLinkPoll() {
        return mLinkPoll;
    }

    public void setLinkPoll(String linkPoll) {
        mLinkPoll = linkPoll;
        notifyPropertyChanged(BR.linkPoll);
    }
}
