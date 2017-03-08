package com.framgia.fpoll.data.model.voteinfo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anhtv on 07/03/2017.
 */
public class ResultVoted {
    @SerializedName("option_id")
    private int mOptionId;
    @SerializedName("count_vote")
    private int mCountVote;

    public int getCountVote() {
        return mCountVote;
    }

    public void setCountVote(int countVote) {
        mCountVote = countVote;
    }

    public int getOptionId() {
        return mOptionId;
    }

    public void setOptionId(int optionId) {
        mOptionId = optionId;
    }
}
