package com.framgia.fpoll.data.model.poll;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fpoll.data.model.FpollComment;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/16/2017.
 * <></>
 */
public class HistoryPoll extends Poll implements Parcelable {
    public static final Creator<Poll> CREATOR = new Creator<Poll>() {
        @Override
        public HistoryPoll createFromParcel(Parcel in) {
            return new HistoryPoll(in);
        }

        @Override
        public HistoryPoll[] newArray(int size) {
            return new HistoryPoll[size];
        }
    };
    @SerializedName("activities")
    private List<FpollComment> mListActivities = new ArrayList<>();

    public HistoryPoll() {
    }

    public HistoryPoll(Parcel in) {
        super(in);
    }

    public HistoryPoll(Parcel in, List<FpollComment> listActivities) {
        super(in);
        mListActivities = listActivities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Bindable
    public List<FpollComment> getListActivities() {
        return mListActivities;
    }

    public void setListActivities(List<FpollComment> listActivities) {
        mListActivities = listActivities;
        notifyPropertyChanged(BR.listActivities);
    }
}
