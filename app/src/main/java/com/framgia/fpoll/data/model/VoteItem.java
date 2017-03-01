package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteItem extends BaseObservable {
    private boolean mIsAnswer;
    private String mContentVote;
    private int mCountVote;
    private String mImageItemVote;

    public VoteItem(boolean checkAnswer, String contentVote, int countVote) {
        mIsAnswer = checkAnswer;
        mContentVote = contentVote;
        mCountVote = countVote;
    }

    public VoteItem(boolean checkAnswer, String contentVote, int countVote, String image) {
        mIsAnswer = checkAnswer;
        mContentVote = contentVote;
        mCountVote = countVote;
        mImageItemVote = image;
    }

    public VoteItem(boolean checkAnswer) {
        mIsAnswer = checkAnswer;
    }

    @Bindable
    public boolean isCheckAnswer() {
        return mIsAnswer;
    }

    public void setCheckAnswer(boolean checkAnswer) {
        mIsAnswer = checkAnswer;
        notifyPropertyChanged(BR.checkAnswer);
    }

    @Bindable
    public String getContentVote() {
        return mContentVote;
    }

    public void setContentVote(String contentVote) {
        mContentVote = contentVote;
        notifyPropertyChanged(BR.contentVote);
    }

    @Bindable
    public int getCountVote() {
        return mCountVote;
    }

    public void setCountVote(int countVote) {
        mCountVote = countVote;
        notifyPropertyChanged(BR.countVote);
    }

    @Bindable
    public boolean isAnswer() {
        return mIsAnswer;
    }

    public void setAnswer(boolean answer) {
        mIsAnswer = answer;
    }

    @Bindable
    public String getImageItemVote() {
        return mImageItemVote;
    }

    public void setImageItemVote(String imageItemVote) {
        mImageItemVote = imageItemVote;
        notifyPropertyChanged(BR.imageItemVote);
    }
}
