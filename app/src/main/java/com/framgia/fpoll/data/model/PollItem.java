package com.framgia.fpoll.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.data.model.poll.PollLink;
import com.framgia.fpoll.util.Constant;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PollItem implements Parcelable {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("multiple")
    private String mMultiple;
    @SerializedName("date_close")
    private String mDateClose;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("links")
    private List<PollLink> mLink;
    private boolean mIsRequireVote;
    private int mRequiteType;
    private boolean mIsSameEmail;
    private boolean mIsMaxVote;
    private int mNumMaxVote;
    private boolean mIsHasPass;
    private String mPass;
    private boolean mIsHideResult;
    private String mMembers;

    protected PollItem(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mEmail = in.readString();
        mTitle = in.readString();
        mDescription = in.readString();
        mMultiple = in.readString();
        mDateClose = in.readString();
        mLocation = in.readString();
        mLink = in.createTypedArrayList(PollLink.CREATOR);
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

    public List<PollLink> getLink() {
        return mLink;
    }

    public void setLink(List<PollLink> link) {
        mLink = link;
    }

    private List<OptionItem> mOptionItemList = new ArrayList<>();

    public List<OptionItem> getOptionItemList() {
        return mOptionItemList;
    }

    public void setOptionItemList(List<OptionItem> optionItemList) {
        mOptionItemList = optionItemList;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return (mDescription != null) ? mDescription : Constant.DataConstant.DATA_SPACE;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getMultiple() {
        return (mMultiple != null) ? mMultiple : Constant.DataConstant.DATA_SPACE;
    }

    public void setMultiple(String multiple) {
        mMultiple = multiple;
    }

    public String getDateClose() {
        return mDateClose;
    }

    public void setDateClose(String dateClose) {
        mDateClose = dateClose;
    }

    public String getLocation() {
        return (mLocation != null) ? mLocation : Constant.DataConstant.DATA_SPACE;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

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

    public PollItem() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mMultiple);
        dest.writeString(mDateClose);
        dest.writeString(mLocation);
        dest.writeTypedList(mLink);
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
