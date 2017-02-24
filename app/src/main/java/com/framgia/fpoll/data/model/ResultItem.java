package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.BitmapRegionDecoder;

import com.framgia.fpoll.BR;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class ResultItem extends BaseObservable {
    private String mNo;
    private String mOption;
    private String mVoteNumber;

    public ResultItem(String no, String option, String voteNumber) {
        mNo = no;
        mOption = option;
        mVoteNumber = voteNumber;
    }

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
}
