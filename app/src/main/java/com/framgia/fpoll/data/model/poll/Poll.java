package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.authorization.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Poll extends BaseObservable implements Parcelable {
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
    private List<Setting> mSettings = new ArrayList<>();
    @SerializedName("options")
    private List<Option> mOptions = new ArrayList<>();
    @SerializedName("comments")
    private List<FpollComment> mComments = new ArrayList<>();
    @SerializedName("links")
    private List<PollLink> mLink = new ArrayList<>();

    public Poll() {
    }

    @Bindable
    public List<PollLink> getLink() {
        return mLink;
    }

    public void setLink(List<PollLink> link) {
        mLink = link;
        notifyPropertyChanged(BR.link);
    }

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

    protected Poll(Parcel in) {
        mId = in.readInt();
        mUserId = in.readString();
        mTitle = in.readString();
        mDescription = in.readString();
        mLocation = in.readString();
        mIsOpen = in.readByte() != 0;
        mIsMultiple = in.readByte() != 0;
        mCreatedTime = in.readString();
        mUpdatedTime = in.readString();
        mDateClose = in.readString();
        mName = in.readString();
        mEmail = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
        mSettings = in.createTypedArrayList(Setting.CREATOR);
        mOptions = in.createTypedArrayList(Option.CREATOR);
        mLink = in.createTypedArrayList(PollLink.CREATOR);
    }

    public static final Creator<Poll> CREATOR = new Creator<Poll>() {
        @Override
        public Poll createFromParcel(Parcel in) {
            return new Poll(in);
        }

        @Override
        public Poll[] newArray(int size) {
            return new Poll[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mUserId);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mLocation);
        dest.writeByte((byte) (mIsOpen ? 1 : 0));
        dest.writeByte((byte) (mIsMultiple ? 1 : 0));
        dest.writeString(mCreatedTime);
        dest.writeString(mUpdatedTime);
        dest.writeString(mDateClose);
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeParcelable(mUser, flags);
        dest.writeTypedList(mSettings);
        dest.writeTypedList(mOptions);
        dest.writeTypedList(mLink);
    }
}
