package com.framgia.fpoll.data.model.poll;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anhtv on 07/03/2017.
 */
public class Option extends BaseObservable {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("image")
    private String mImage;
    @SerializedName("poll_id")
    private int mPollId;
    @SerializedName("created_at")
    private String mCreatedTime;
    @SerializedName("updated_at")
    private String mUpdatedTime;
    @SerializedName("votes")
    private List<Vote> mVotes;
    @SerializedName("participant_votes")
    private List<ParticipantVotes> mParticipantVotes;

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    public void setImage(String image) {
        mImage = image;
        notifyPropertyChanged(BR.image);
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
        notifyPropertyChanged(BR.createdTime);
    }

    public void setUpdatedTime(String updatedTime) {
        mUpdatedTime = updatedTime;
        notifyPropertyChanged(BR.updatedTime);
    }

    public void setVotes(List<Vote> votes) {
        mVotes = votes;
        notifyPropertyChanged(BR.votes);
    }

    public void setParticipantVotes(List<ParticipantVotes> participantVotes) {
        mParticipantVotes = participantVotes;
        notifyPropertyChanged(BR.participantVotes);
    }

    @Bindable
    public int getId() {
        return mId;
    }

    @Bindable
    public String getImage() {
        return mImage;
    }

    @Bindable
    public int getPollId() {
        return mPollId;
    }

    @Bindable
    public String getCreatedTime() {
        return mCreatedTime;
    }

    @Bindable
    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    @Bindable
    public List<Vote> getVotes() {
        return mVotes;
    }

    @Bindable
    public List<ParticipantVotes> getParticipantVotes() {
        return mParticipantVotes;
    }
}
