package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.Poll;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by tuanbg on 3/6/17.
 */
public class DataInfoItem extends BaseObservable implements Parcelable {
    @SerializedName("poll")
    private PollItem mPoll;
    @SerializedName("countParticipant")
    private int mCountParticipant;
    @SerializedName("countComments")
    private int mCountComments;
    @SerializedName("activities")
    private List<FpollComment> mFpollComment;
    @SerializedName("token_type")
    private int mTokenType;

    @Bindable
    public List<FpollComment> getFpollComment() {
        return mFpollComment;
    }

    public void setFpollComment(List<FpollComment> fpollComment) {
        this.mFpollComment = fpollComment;
        notifyPropertyChanged(BR.fpollComment);
    }

    @Bindable
    public PollItem getPoll() {
        return mPoll;
    }

    public void setPoll(PollItem poll) {
        this.mPoll = poll;
        notifyPropertyChanged(BR.poll);
    }

    @Bindable
    public int getTokenType() {
        return mTokenType;
    }

    public void setTokenType(int tokenType) {
        mTokenType = tokenType;
        notifyPropertyChanged(BR.tokenType);
    }

    @Bindable
    public int getCountParticipant() {
        return mCountParticipant;
    }

    public void setCountParticipant(int countParticipant) {
        mCountParticipant = countParticipant;
        notifyPropertyChanged(BR.countParticipant);
    }

    @Bindable
    public int getCountComments() {
        return mCountComments;
    }

    public void setCountComments(int countComments) {
        mCountComments = countComments;
        notifyPropertyChanged(BR.countComments);
    }

    public static final Creator<DataInfoItem> CREATOR = new Creator<DataInfoItem>() {
        @Override
        public DataInfoItem createFromParcel(Parcel in) {
            return new DataInfoItem(in);
        }

        @Override
        public DataInfoItem[] newArray(int size) {
            return new DataInfoItem[size];
        }
    };

    public DataInfoItem(Parcel in) {
        mPoll = in.readParcelable(Poll.class.getClassLoader());
        mCountParticipant = in.readInt();
        mCountComments = in.readInt();
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

    public DataInfoItem() {
    }
}
