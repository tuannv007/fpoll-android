package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 07/03/2017.
 */
public class ParticipantVotes extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private String mId;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("ip_address")
    private String mIpAddress;
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;

    protected ParticipantVotes(Parcel in) {
        mId = in.readString();
        mUserId = in.readString();
        mIpAddress = in.readString();
        mName = in.readString();
        mEmail = in.readString();
        mCreatedTime = in.readString();
        mUpdatedTime = in.readString();
    }

    public static final Creator<ParticipantVotes> CREATOR = new Creator<ParticipantVotes>() {
        @Override
        public ParticipantVotes createFromParcel(Parcel in) {
            return new ParticipantVotes(in);
        }

        @Override
        public ParticipantVotes[] newArray(int size) {
            return new ParticipantVotes[size];
        }
    };

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
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
    public String getIpAddress() {
        return mIpAddress;
    }

    public void setIpAddress(String ipAddress) {
        mIpAddress = ipAddress;
        notifyPropertyChanged(BR.ipAddress);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mUserId);
        dest.writeString(mIpAddress);
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeString(mCreatedTime);
        dest.writeString(mUpdatedTime);
    }
}
