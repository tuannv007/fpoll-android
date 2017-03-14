package com.framgia.fpoll.data.model.poll;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 10/03/2017.
 */
public class PollLink implements Parcelable {
    @SerializedName("token")
    private String mToken;
    @SerializedName("link_admin")
    private Boolean mLinkAdmin;
    @SerializedName("poll_id")
    private int mPollId;

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public Boolean getLinkAdmin() {
        return mLinkAdmin;
    }

    public void setLinkAdmin(Boolean linkAdmin) {
        mLinkAdmin = linkAdmin;
    }

    public int getPollId() {
        return mPollId;
    }

    public void setPollId(int pollId) {
        mPollId = pollId;
    }

    protected PollLink(Parcel in) {
        mToken = in.readString();
        byte mLinkAdminVal = in.readByte();
        mLinkAdmin = mLinkAdminVal == 0x02 ? null : mLinkAdminVal != 0x00;
        mPollId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mToken);
        if (mLinkAdmin == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (mLinkAdmin ? 0x01 : 0x00));
        }
        dest.writeInt(mPollId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PollLink> CREATOR = new Parcelable.Creator<PollLink>() {
        @Override
        public PollLink createFromParcel(Parcel in) {
            return new PollLink(in);
        }

        @Override
        public PollLink[] newArray(int size) {
            return new PollLink[size];
        }
    };
}
