package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Setting extends BaseObservable implements Parcelable {
    public static final Creator<Setting> CREATOR = new Creator<Setting>() {
        @Override
        public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        @Override
        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };
    @SerializedName("id")
    private int mId;
    @SerializedName("poll_id")
    private int mPollId;
    @SerializedName("key")
    private int mKey;
    @SerializedName("value")
    private String mValue;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;

    protected Setting(Parcel in) {
        mId = in.readInt();
        mPollId = in.readInt();
        mKey = in.readInt();
        mValue = in.readString();
        mCreatedTime = in.readString();
        mUpdatedTime = in.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public int getPollId() {
        return mPollId;
    }

    public void setPollId(int pollId) {
        mPollId = pollId;
        notifyPropertyChanged(BR.pollId);
    }

    @Bindable
    public int getKey() {
        return mKey;
    }

    public void setKey(int key) {
        mKey = key;
        notifyPropertyChanged(BR.key);
    }

    @Bindable
    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
        notifyPropertyChanged(BR.value);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mPollId);
        dest.writeInt(mKey);
        dest.writeString(mValue);
        dest.writeString(mCreatedTime);
        dest.writeString(mUpdatedTime);
    }
}
