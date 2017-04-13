package com.framgia.fpoll.ui.pollcreation.setting;

/**
 * Created by Nhahv0902 on 3/24/2017.
 * <></>
 */
public enum KeySetting {
    REQUIRE_VOTE(0), EMAIL(1), HIDE_RESULT(2), EDIT_LINK(3), NUMBER_VOTE(4), PASSWORD(5), NONE(
            6), NAME(7), NAME_EMAIL(8), ALL_NEW_OPTION(9), NOT_COINCIDENT(10), EDIT_OPTION(
            11), ADD_TYPE_EMAIL(12);
    private int mType;

    KeySetting(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
