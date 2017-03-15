package com.framgia.fpoll.ui.votemanager.vote;

/**
 * Created by anhtv on 15/03/2017.
 */
public enum VoteType {
    TYPE_SINGLE(1), TYPE_MULTIPLE(2);
    private int mValue;

    VoteType(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
