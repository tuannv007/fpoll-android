package com.framgia.fpoll.data.ApiRestClient.APIService.pollmanager;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tuanbg on 3/6/17.
 */
public class DataInfoItem extends BaseObservable implements Parcelable {
    public final Creator<DataInfoItem> CREATOR = new Creator<DataInfoItem>() {
        @Override
        public DataInfoItem createFromParcel(Parcel in) {
            return new DataInfoItem(in);
        }

        @Override
        public DataInfoItem[] newArray(int size) {
            return new DataInfoItem[size];
        }
    };
    @SerializedName("poll")
    private Poll mPoll;
    @SerializedName("countParticipant")
    private int mCountParticipant;
    @SerializedName("countComments")
    private int mCountComments;

    public DataInfoItem(Parcel in) {
        mPoll = in.readParcelable(Poll.class.getClassLoader());
        mCountParticipant = in.readInt();
        mCountComments = in.readInt();
    }

    @Bindable
    public Poll getPoll() {
        return mPoll;
    }

    public void setPoll(Poll poll) {
        this.mPoll = poll;
        notifyPropertyChanged(BR.poll);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mPoll, flags);
        dest.writeInt(mCountParticipant);
        dest.writeInt(mCountComments);
    }

    public class Poll extends BaseObservable implements Parcelable {
        public final Creator<Poll> CREATOR = new Creator<Poll>() {
            @Override
            public Poll createFromParcel(Parcel in) {
                return new Poll(in);
            }

            @Override
            public Poll[] newArray(int size) {
                return new Poll[size];
            }
        };
        @SerializedName("id")
        private int mId;
        @SerializedName("user_id")
        private Object mUserId;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("location")
        private String mLocation;
        @SerializedName("status")
        private boolean mStatus;
        @SerializedName("multiple")
        private boolean mMultiple;
        @SerializedName("created_at")
        private String mCreatedAt;
        @SerializedName("updated_at")
        private String mUpdatedAt;
        @SerializedName("date_close")
        private String mDateClose;
        @SerializedName("name")
        private String mName;
        @SerializedName("email")
        private String mEmail;
        @SerializedName("user")
        private User mUser;

        protected Poll(Parcel in) {
            mId = in.readInt();
            mTitle = in.readString();
            mDescription = in.readString();
            mLocation = in.readString();
            mStatus = in.readByte() != 0;
            mMultiple = in.readByte() != 0;
            mCreatedAt = in.readString();
            mUpdatedAt = in.readString();
            mDateClose = in.readString();
            mName = in.readString();
            mEmail = in.readString();
        }

        @Bindable
        public int getId() {
            return mId;
        }

        public void setId(int id) {
            this.mId = id;
            notifyPropertyChanged(BR.id);
        }

        @Bindable
        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            this.mTitle = title;
            notifyPropertyChanged(BR.title);
        }

        @Bindable
        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            this.mDescription = description;
            notifyPropertyChanged(BR.description);
        }

        @Bindable
        public String getLocation() {
            return mLocation;
        }

        public void setLocation(String location) {
            this.mLocation = location;
            notifyPropertyChanged(BR.location);
        }

        @Bindable
        public boolean isStatus() {
            return mStatus;
        }

        public void setStatus(boolean status) {
            this.mStatus = status;
            notifyPropertyChanged(BR.status);
        }

        @Bindable
        public boolean isMultiple() {
            return mMultiple;
        }

        public void setMultiple(boolean multiple) {
            this.mMultiple = multiple;
            notifyPropertyChanged(BR.multiple);
        }

        @Bindable
        public String getCreatedAt() {
            return mCreatedAt;
        }

        public void setCreatedAt(String createdAt) {
            this.mCreatedAt = createdAt;
            notifyPropertyChanged(BR.createdAt);
        }

        @Bindable
        public String getName() {
            return mName;
        }

        public void setName(String name) {
            this.mName = name;
            notifyPropertyChanged(BR.name);
        }

        @Bindable
        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            this.mEmail = email;
            notifyPropertyChanged(BR.email);
        }

        @Bindable
        public Object getUser() {
            return mUser;
        }

        public void setUser(User user) {
            this.mUser = user;
            notifyPropertyChanged(BR.user);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mId);
            dest.writeString(mTitle);
            dest.writeString(mDescription);
            dest.writeString(mLocation);
            dest.writeByte((byte) (mStatus ? 1 : 0));
            dest.writeByte((byte) (mMultiple ? 1 : 0));
            dest.writeString(mCreatedAt);
            dest.writeString(mUpdatedAt);
            dest.writeString(mDateClose);
            dest.writeString(mName);
            dest.writeString(mEmail);
        }
    }
}
