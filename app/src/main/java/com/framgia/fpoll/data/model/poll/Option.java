package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Option extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("url_image")
    private String mImage;
    @SerializedName("poll_id")
    private int mPollId;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;
    @SerializedName("users")
    private List<ParticipantVotes> mVotes;
    @SerializedName("participants")
    private List<ParticipantVotes> mParticipantVotes;
    private boolean mIsChecked;

    public Option() {
    }

    public Option(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mImage = in.readString();
        mPollId = in.readInt();
        mCreatedTime = in.readString();
        mUpdatedTime = in.readString();
        mIsChecked = in.readByte() != 0;
    }

    public static final Creator<Option> CREATOR = new Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
        notifyPropertyChanged(BR.checked);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public void setImage(String image) {
        mImage = image;
        notifyPropertyChanged(BR.image);
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
        notifyPropertyChanged(BR.createdTime);
    }

    public void setUpdatedTime(String updatedTime) {
        mUpdatedTime = updatedTime;
        notifyPropertyChanged(BR.updatedTime);
    }

    public void setVotes(List<ParticipantVotes> votes) {
        mVotes = votes;
        notifyPropertyChanged(BR.votes);
    }

    public void setParticipantVotes(List<ParticipantVotes> participantVotes) {
        mParticipantVotes = participantVotes;
        notifyPropertyChanged(BR.participantVotes);
    }

    @Bindable
    public String getImage() {
        return mImage;
    }

    @Bindable
    public int getPollId() {
        return mPollId;
    }

    @Bindable
    public String getCreatedTime() {
        return mCreatedTime;
    }

    @Bindable
    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    @Bindable
    public List<ParticipantVotes> getVotes() {
        return mVotes;
    }

    @Bindable
    public List<ParticipantVotes> getParticipantVotes() {
        return mParticipantVotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeInt(mPollId);
        dest.writeString(mCreatedTime);
        dest.writeString(mUpdatedTime);
        dest.writeByte((byte) (mIsChecked ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Option{" +
            "mId=" + mId +
            ", mName='" + mName + '\'' +
            ", mImage='" + mImage + '\'' +
            ", mPollId=" + mPollId +
            ", mCreatedTime='" + mCreatedTime + '\'' +
            ", mUpdatedTime='" + mUpdatedTime + '\'' +
            ", mVotes=" + mVotes +
            ", mParticipantVotes=" + mParticipantVotes +
            '}';
    }
}
