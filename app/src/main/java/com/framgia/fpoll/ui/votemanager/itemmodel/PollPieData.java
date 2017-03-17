package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.List;

/**
 * Created by anhtv on 20/03/2017.
 */
public class PollPieData extends PieData implements Parcelable {
    public PollPieData(List<String> xVals, PieDataSet dataSet) {
        super(xVals, dataSet);
    }

    protected PollPieData(Parcel in) {
    }

    public static final Creator<PollPieData> CREATOR =
        new Creator<PollPieData>() {
            @Override
            public PollPieData createFromParcel(Parcel in) {
                return new PollPieData(in);
            }

            @Override
            public PollPieData[] newArray(int size) {
                return new PollPieData[size];
            }
        };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
