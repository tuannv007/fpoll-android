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
public class ResultVoted extends BaseObservable implements Parcelable {
    @SerializedName("option_id")
    private int mOptionId;
    @SerializedName("count_vote")
    private int mCountVote;

    protected ResultVoted(Parcel in) {
        mOptionId = in.readInt();
        mCountVote = in.readInt();
    }

    public static final Creator<ResultVoted> CREATOR = new Creator<ResultVoted>() {
        @Override
        public ResultVoted createFromParcel(Parcel in) {
            return new ResultVoted(in);
        }

        @Override
        public ResultVoted[] newArray(int size) {
            return new ResultVoted[size];
        }
    };

    @Bindable
    public int getCountVote() {
        return mCountVote;
    }

    public void setCountVote(int countVote) {
        mCountVote = countVote;
        notifyPropertyChanged(BR.countVote);
    }

    @Bindable
    public int getOptionId() {
        return mOptionId;
    }

    public void setOptionId(int optionId) {
        mOptionId = optionId;
        notifyPropertyChanged(BR.optionId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mOptionId);
        dest.writeInt(mCountVote);
    }
}
