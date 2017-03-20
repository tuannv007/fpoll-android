package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;

import java.util.List;

/**
 * Created by anhtv on 20/03/2017.
 */
public class PollBarData extends BarData implements Parcelable {
    public PollBarData(List<String> xVals, BarDataSet dataSet) {
        super(xVals, dataSet);
    }

    protected PollBarData(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PollBarData> CREATOR
        = new Creator<PollBarData>() {
        @Override
        public PollBarData createFromParcel(Parcel in) {
            return new PollBarData(in);
        }

        @Override
        public PollBarData[] newArray(int size) {
            return new PollBarData[size];
        }
    };
}
