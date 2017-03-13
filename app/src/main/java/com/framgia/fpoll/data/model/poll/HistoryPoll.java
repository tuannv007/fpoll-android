package com.framgia.fpoll.data.model.poll;

import android.databinding.Bindable;
import android.os.Parcel;

import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fpoll.data.model.FpollComment;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nhahv0902 on 2/16/2017.
 * <></>
 */
public class HistoryPoll extends Poll {
    @SerializedName("activities")
    private List<FpollComment> mListActivities = new ArrayList<>();

    protected HistoryPoll(Parcel in) {
        super(in);
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
