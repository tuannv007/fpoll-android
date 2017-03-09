package com.framgia.fpoll.data.model.voteinfo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Poll extends BaseObservable {
    @SerializedName("id")
    private int mId;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("status")
    private boolean mIsOpen;
    @SerializedName("multiple")
    private boolean mIsMultiple;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;
    @SerializedName("date_close")
    private String mDateClose;
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("user")
    private User mUser;
    @SerializedName("settings")
    private List<Setting> mSettings;
    @SerializedName("options")
    private List<Option> mOptions;
    @SerializedName("comments")
    private List<FpollComment> mComments;

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
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
    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
        notifyPropertyChanged(BR.location);
    }

    @Bindable
    public boolean isOpen() {
        return mIsOpen;
    }

    public void setOpen(boolean open) {
        mIsOpen = open;
        notifyPropertyChanged(BR.open);
    }

    @Bindable
    public boolean isMultiple() {
        return mIsMultiple;
    }

    public void setMultiple(boolean multiple) {
        mIsMultiple = multiple;
        notifyPropertyChanged(BR.multiple);
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
    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        mUpdatedTime = updatedTime;
        notifyPropertyChanged(BR.updatedTime);
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
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
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
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public List<Setting> getSettings() {
        return mSettings;
    }

    public void setSettings(List<Setting> settings) {
        mSettings = settings;
        notifyPropertyChanged(BR.settings);
    }

    @Bindable
    public List<Option> getOptions() {
        return mOptions;
    }

    public void setOptions(List<Option> options) {
        mOptions = options;
        notifyPropertyChanged(BR.options);
    }

    @Bindable
    public List<FpollComment> getComments() {
        return mComments;
    }

    public void setComments(List<FpollComment> comments) {
        mComments = comments;
        notifyPropertyChanged(BR.comments);
    }
}
