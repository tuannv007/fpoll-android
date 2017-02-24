package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.BitmapRegionDecoder;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;

import java.io.Serializable;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class ResultItem extends BaseObservable implements Parcelable {
    private String mNo;
    private String mOption;
    private String mVoteNumber;

    public ResultItem(String no, String option, String voteNumber) {
        mNo = no;
        mOption = option;
        mVoteNumber = voteNumber;
    }

    protected ResultItem(Parcel in) {
        mNo = in.readString();
        mOption = in.readString();
        mVoteNumber = in.readString();
    }

    public static final Creator<ResultItem> CREATOR = new Creator<ResultItem>() {
        @Override
        public ResultItem createFromParcel(Parcel in) {
            return new ResultItem(in);
        }

        @Override
        public ResultItem[] newArray(int size) {
            return new ResultItem[size];
        }
    };

    @Bindable
    public String getNo() {
        return mNo;
    }

    public void setNo(String no) {
        mNo = no;
        notifyPropertyChanged(BR.no);
    }

    @Bindable
    public String getOption() {
        return mOption;
    }

    public void setOption(String option) {
        mOption = option;
        notifyPropertyChanged(BR.option);
    }

    @Bindable
    public String getVoteNumber() {
        return mVoteNumber;
    }

    public void setVoteNumber(String voteNumber) {
        mVoteNumber = voteNumber;
        notifyPropertyChanged(BR.voteNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mNo);
        parcel.writeString(mOption);
        parcel.writeString(mVoteNumber);
    }
}
