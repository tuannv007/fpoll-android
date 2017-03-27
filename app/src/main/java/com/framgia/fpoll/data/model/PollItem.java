package com.framgia.fpoll.data.model;

import android.databinding.Bindable;
import android.os.Parcel;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.Poll;

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
    private boolean mCoincidentEmail;
    private boolean mIsOptimizeLink;
    private String mTextOptimizeLink;

    public PollItem() {
    }

    @Bindable
    public boolean isCoincidentEmail() {
        return mCoincidentEmail;
    }

    @Bindable
    public boolean isRequireVote() {
        return mIsRequireVote;
    }

    @Bindable
    public int getRequiteType() {
        return mRequiteType;
    }

    @Bindable
    public boolean isSameEmail() {
        return mIsSameEmail;
    }

    @Bindable
    public boolean isMaxVote() {
        return mIsMaxVote;
    }

    @Bindable
    public int getNumMaxVote() {
        return mNumMaxVote;
    }

    @Bindable
    public boolean isHasPass() {
        return mIsHasPass;
    }

    @Bindable
    public String getPass() {
        return mPass;
    }

    @Bindable
    public boolean isHideResult() {
        return mIsHideResult;
    }

    @Bindable
    public String getMembers() {
        return mMembers;
    }

    @Bindable
    public boolean isAllowAddOption() {
        return mIsAllowAddOption;
    }

    @Bindable
    public boolean isAllowEditOption() {
        return mIsAllowEditOption;
    }

    @Bindable
    public boolean isOptimizeLink() {
        return mIsOptimizeLink;
    }

    @Bindable
    public String getTextOptimizeLink() {
        return mTextOptimizeLink;
    }

    public void setRequireVote(boolean requireVote) {
        mIsRequireVote = requireVote;
        notifyPropertyChanged(BR.requireVote);
    }

    public void setRequiteType(int requiteType) {
        mRequiteType = requiteType;
        notifyPropertyChanged(BR.requiteType);
    }

    public void setSameEmail(boolean sameEmail) {
        mIsSameEmail = sameEmail;
        notifyPropertyChanged(BR.sameEmail);
    }

    public void setMaxVote(boolean maxVote) {
        mIsMaxVote = maxVote;
        notifyPropertyChanged(BR.maxVote);
    }

    public void setNumMaxVote(int numMaxVote) {
        mNumMaxVote = numMaxVote;
        notifyPropertyChanged(BR.numMaxVote);
    }

    public void setOptimizeLink(boolean optimizeLink) {
        mIsOptimizeLink = optimizeLink;
        notifyPropertyChanged(BR.optimizeLink);
    }

    public void setTextOptimizeLink(String textOptimizeLink) {
        mTextOptimizeLink = textOptimizeLink;
        notifyPropertyChanged(BR.textOptimizeLink);
    }

    public void setHasPass(boolean hasPass) {
        mIsHasPass = hasPass;
        notifyPropertyChanged(BR.hasPass);
    }

    public void setPass(String pass) {
        mPass = pass;
        notifyPropertyChanged(BR.pass);
    }

    public void setHideResult(boolean hideResult) {
        mIsHideResult = hideResult;
        notifyPropertyChanged(BR.hideResult);
    }

    public void setMembers(String members) {
        mMembers = members;
        notifyPropertyChanged(BR.members);
    }

    public void setAllowAddOption(boolean allowAddOption) {
        mIsAllowAddOption = allowAddOption;
        notifyPropertyChanged(BR.allowAddOption);
    }

    public void setAllowEditOption(boolean allowEditOption) {
        mIsAllowEditOption = allowEditOption;
        notifyPropertyChanged(BR.allowEditOption);
    }

    public void setCoincidentEmail(boolean coincidentEmail) {
        mCoincidentEmail = coincidentEmail;
        notifyPropertyChanged(BR.coincidentEmail);
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
