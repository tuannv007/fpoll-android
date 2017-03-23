package com.framgia.fpoll.data.model;

import android.os.Parcel;

import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.util.Constant;

public class PollItem extends Poll {
    private boolean mIsRequireVote;
    private int mRequiteType;
    private boolean mIsSameEmail;
    private boolean mIsMaxVote;
    private int mNumMaxVote;
    private boolean mIsHasPass;
    private String mPass;
    private boolean mIsHideResult;
    private String mMembers;
    private boolean mIsAllowAddOption;
    private boolean mIsAllowEditOption;

    public boolean isRequireVote() {
        return mIsRequireVote;
    }

    public void setRequireVote(boolean requireVote) {
        mIsRequireVote = requireVote;
    }

    public int getRequiteType() {
        return mRequiteType;
    }

    public void setRequiteType(int requiteType) {
        mRequiteType = requiteType;
    }

    public boolean isSameEmail() {
        return mIsSameEmail;
    }

    public void setSameEmail(boolean sameEmail) {
        mIsSameEmail = sameEmail;
    }

    public boolean isMaxVote() {
        return mIsMaxVote;
    }

    public void setMaxVote(boolean maxVote) {
        mIsMaxVote = maxVote;
    }

    public int getNumMaxVote() {
        return mNumMaxVote;
    }

    public void setNumMaxVote(int numMaxVote) {
        mNumMaxVote = numMaxVote;
    }

    public boolean isHasPass() {
        return mIsHasPass;
    }

    public void setHasPass(boolean hasPass) {
        mIsHasPass = hasPass;
    }

    public String getPass() {
        return (mPass != null) ? mPass : Constant.DataConstant.DATA_SPACE;
    }

    public void setPass(String pass) {
        mPass = pass;
    }

    public boolean isHideResult() {
        return mIsHideResult;
    }

    public void setHideResult(boolean hideResult) {
        mIsHideResult = hideResult;
    }

    public String getMembers() {
        return (mMembers != null) ? mMembers : Constant.DataConstant.DATA_SPACE;
    }

    public void setMembers(String members) {
        mMembers = members;
    }

    public boolean isAllowAddOption() {
        return mIsAllowAddOption;
    }

    public void setAllowAddOption(boolean allowAddOption) {
        mIsAllowAddOption = allowAddOption;
    }

    public boolean isAllowEditOption() {
        return mIsAllowEditOption;
    }

    public void setAllowEditOption(boolean allowEditOption) {
        mIsAllowEditOption = allowEditOption;
    }

    public PollItem() {
    }

    public static final Creator<PollItem> CREATOR = new Creator<PollItem>() {
        @Override
        public PollItem createFromParcel(Parcel in) {
            return new PollItem(in);
        }

        @Override
        public PollItem[] newArray(int size) {
            return new PollItem[size];
        }
    };

    protected PollItem(Parcel in) {
        super(in);
        mIsRequireVote = in.readByte() != 0;
        mRequiteType = in.readInt();
        mIsSameEmail = in.readByte() != 0;
        mIsMaxVote = in.readByte() != 0;
        mNumMaxVote = in.readInt();
        mIsHasPass = in.readByte() != 0;
        mPass = in.readString();
        mIsHideResult = in.readByte() != 0;
        mMembers = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (mIsRequireVote ? 1 : 0));
        dest.writeInt(mRequiteType);
        dest.writeByte((byte) (mIsSameEmail ? 1 : 0));
        dest.writeByte((byte) (mIsMaxVote ? 1 : 0));
        dest.writeInt(mNumMaxVote);
        dest.writeByte((byte) (mIsHasPass ? 1 : 0));
        dest.writeString(mPass);
        dest.writeByte((byte) (mIsHideResult ? 1 : 0));
        dest.writeString(mMembers);
    }
}
