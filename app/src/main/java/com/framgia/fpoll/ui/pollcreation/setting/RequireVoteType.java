package com.framgia.fpoll.ui.pollcreation.setting;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <></>
 */
public enum RequireVoteType {
    NAME(1), EMAIL(7), NAME_EMAIL(8);
    private int mValue;

    RequireVoteType(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
