package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.Poll;
import com.google.gson.annotations.SerializedName;

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

    @Bindable
    public PollItem getPoll() {
        return mPoll;
    }

    public void setPoll(PollItem poll) {
        this.mPoll = poll;
        notifyPropertyChanged(BR.poll);
    }

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
}
