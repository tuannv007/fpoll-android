package com.framgia.fpoll.data.model.voteinfo;

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
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
        notifyPropertyChanged(BR.image);
    }

    @Bindable
    public int getPollId() {
        return mPollId;
    }

    public void setPollId(int pollId) {
        mPollId = pollId;
        notifyPropertyChanged(BR.pollId);
    }

    @Bindable
    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
        notifyPropertyChanged(BR.createdTime);
    }

    @Bindable
    public String getUpdatedTime() {
        return mUpdatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        mUpdatedTime = updatedTime;
        notifyPropertyChanged(BR.updatedTime);
    }

    @Bindable
    public List<Vote> getVotes() {
        return mVotes;
    }

    public void setVotes(List<Vote> votes) {
        mVotes = votes;
        notifyPropertyChanged(BR.votes);
    }

    @Bindable
    public List<ParticipantVotes> getParticipantVotes() {
        return mParticipantVotes;
    }

    public void setParticipantVotes(List<ParticipantVotes> participantVotes) {
        mParticipantVotes = participantVotes;
        notifyPropertyChanged(BR.participantVotes);
    }
}
