package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anhtv on 07/03/2017.
 */
public class VoteInfo extends BaseObservable implements Serializable {
    private String mId;
    @SerializedName("poll")
    private Poll mPoll;
    @SerializedName("countParticipant")
    private int mCountParticipant;
    @SerializedName("countComments")
    private int mCountComments;
    @SerializedName("result_voted")
    private List<ResultVoted> mResultVoted;

    @Bindable
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public Poll getPoll() {
        return mPoll;
    }

    public void setPoll(Poll poll) {
        mPoll = poll;
        notifyPropertyChanged(BR.poll);
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

    @Bindable
    public List<ResultVoted> getResultVoted() {
        return mResultVoted;
    }

    public void setResultVoted(List<ResultVoted> resultVoted) {
        mResultVoted = resultVoted;
        notifyPropertyChanged(BR.resultVoted);
    }
}
