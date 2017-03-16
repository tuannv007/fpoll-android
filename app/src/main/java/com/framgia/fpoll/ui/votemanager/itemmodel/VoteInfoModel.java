package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anhtv on 09/03/2017.
 */
public class VoteInfoModel extends BaseObservable implements Serializable {
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
    private PieData mPieData;
    private BarData mBarData;
    private ItemStatus mItemStatus = ItemStatus.NOT_AVAILABLE;

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
    public PieData getPieData() {
        return mPieData;
    }

    public void setPieData(PieData pieData) {
        mPieData = pieData;
        notifyPropertyChanged(BR.pieData);
    }

    @Bindable
    public BarData getBarData() {
        return mBarData;
    }

    public void setBarData(BarData barData) {
        mBarData = barData;
        notifyPropertyChanged(BR.barData);
    }
}
