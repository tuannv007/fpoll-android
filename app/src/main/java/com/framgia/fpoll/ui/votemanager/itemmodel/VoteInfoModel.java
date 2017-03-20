package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.VoteInfo;

import java.util.List;

/**
 * Created by anhtv on 09/03/2017.
 */
public class VoteInfoModel extends BaseObservable implements Parcelable {
    private VoteInfo mVoteInfo;
    private List<OptionModel> mOptionModels;
    private boolean mIsEmailRequired;
    private boolean mIsNameRequired;
    private boolean mIsEmailAndNameRequired;
    private int mNumberVoteLimit;
    private String mPasswordRequired;
    private String mLinkEdited;
    private boolean mAbleToAddOption;
    private boolean mSpecificEmail;
    private boolean mOptionEditable;
    private boolean mHiddenResult;
    private PollPieData mPieData;
    private PollBarData mBarData;
    private String mToken;
    private ItemStatus mItemStatus = ItemStatus.NOT_AVAILABLE;

    public VoteInfoModel() {
        //default vote number limit
        mNumberVoteLimit = -1;
    }

    protected VoteInfoModel(Parcel in) {
        mVoteInfo = in.readParcelable(VoteInfo.class.getClassLoader());
        mIsEmailRequired = in.readByte() != 0;
        mIsNameRequired = in.readByte() != 0;
        mIsEmailAndNameRequired = in.readByte() != 0;
        mNumberVoteLimit = in.readInt();
        mPasswordRequired = in.readString();
        mLinkEdited = in.readString();
        mAbleToAddOption = in.readByte() != 0;
        mSpecificEmail = in.readByte() != 0;
        mOptionEditable = in.readByte() != 0;
        mHiddenResult = in.readByte() != 0;
        mToken = in.readString();
    }

    public static final Creator<VoteInfoModel> CREATOR = new Creator<VoteInfoModel>() {
        @Override
        public VoteInfoModel createFromParcel(Parcel in) {
            return new VoteInfoModel(in);
        }

        @Override
        public VoteInfoModel[] newArray(int size) {
            return new VoteInfoModel[size];
        }
    };

    @Bindable
    public ItemStatus getItemStatus() {
        return mItemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        mItemStatus = itemStatus;
        notifyPropertyChanged(BR.itemStatus);
    }

    @Bindable
    public VoteInfo getVoteInfo() {
        return mVoteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        mVoteInfo = voteInfo;
        notifyPropertyChanged(BR.voteInfo);
    }

    @Bindable
    public List<OptionModel> getOptionModels() {
        return mOptionModels;
    }

    public void setOptionModels(List<OptionModel> optionModels) {
        mOptionModels = optionModels;
        notifyPropertyChanged(BR.optionModel);
    }

    @Bindable
    public boolean isEmailRequired() {
        return mIsEmailRequired;
    }

    public void setEmailRequired(boolean emailRequired) {
        mIsEmailRequired = emailRequired;
        notifyPropertyChanged(BR.emailRequired);
    }

    @Bindable
    public boolean isNameRequired() {
        return mIsNameRequired;
    }

    public void setNameRequired(boolean nameRequired) {
        mIsNameRequired = nameRequired;
        notifyPropertyChanged(BR.nameRequired);
    }

    @Bindable
    public boolean isEmailAndNameRequired() {
        return mIsEmailAndNameRequired;
    }

    public void setEmailAndNameRequired(boolean emailAndNameRequired) {
        mIsEmailAndNameRequired = emailAndNameRequired;
        notifyPropertyChanged(BR.emailAndNameRequired);
    }

    @Bindable
    public int getNumberVoteLimit() {
        return mNumberVoteLimit;
    }

    public void setNumberVoteLimit(int numberVoteLimit) {
        mNumberVoteLimit = numberVoteLimit;
        notifyPropertyChanged(BR.numberVoteLimit);
    }

    @Bindable
    public String getPasswordRequired() {
        return mPasswordRequired;
    }

    public void setPasswordRequired(String passwordRequired) {
        mPasswordRequired = passwordRequired;
        notifyPropertyChanged(BR.passwordRequired);
    }

    @Bindable
    public String getLinkEdited() {
        return mLinkEdited;
    }

    public void setLinkEdited(String linkEdited) {
        mLinkEdited = linkEdited;
        notifyPropertyChanged(BR.linkEdited);
    }

    @Bindable
    public boolean isAbleToAddOption() {
        return mAbleToAddOption;
    }

    public void setAbleToAddOption(boolean ableToAddOption) {
        mAbleToAddOption = ableToAddOption;
        notifyPropertyChanged(BR.ableToAddOption);
    }

    @Bindable
    public boolean isSpecificEmail() {
        return mSpecificEmail;
    }

    public void setSpecificEmail(boolean specificEmail) {
        mSpecificEmail = specificEmail;
        notifyPropertyChanged(BR.specificEmail);
    }

    @Bindable
    public boolean isOptionEditable() {
        return mOptionEditable;
    }

    public void setOptionEditable(boolean optionEditable) {
        mOptionEditable = optionEditable;
        notifyPropertyChanged(BR.optionEditable);
    }

    @Bindable
    public boolean isHiddenResult() {
        return mHiddenResult;
    }

    public void setHiddenResult(boolean hiddenResult) {
        mHiddenResult = hiddenResult;
        notifyPropertyChanged(BR.hiddenResult);
    }

    @Bindable
    public PollPieData getPieData() {
        return mPieData;
    }

    public void setPieData(PollPieData pieData) {
        mPieData = pieData;
        notifyPropertyChanged(BR.pieData);
    }

    @Bindable
    public PollBarData getBarData() {
        return mBarData;
    }

    public void setBarData(PollBarData barData) {
        mBarData = barData;
        notifyPropertyChanged(BR.barData);
    }

    @Bindable
    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
        notifyPropertyChanged(BR.token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mVoteInfo, flags);
        dest.writeByte((byte) (mIsEmailRequired ? 1 : 0));
        dest.writeByte((byte) (mIsNameRequired ? 1 : 0));
        dest.writeByte((byte) (mIsEmailAndNameRequired ? 1 : 0));
        dest.writeInt(mNumberVoteLimit);
        dest.writeString(mPasswordRequired);
        dest.writeString(mLinkEdited);
        dest.writeByte((byte) (mAbleToAddOption ? 1 : 0));
        dest.writeByte((byte) (mSpecificEmail ? 1 : 0));
        dest.writeByte((byte) (mOptionEditable ? 1 : 0));
        dest.writeByte((byte) (mHiddenResult ? 1 : 0));
        dest.writeString(mToken);
    }
}
