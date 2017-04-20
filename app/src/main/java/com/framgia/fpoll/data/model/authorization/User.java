package com.framgia.fpoll.data.model.authorization;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.fpoll.BR;
import com.framgia.fpoll.R;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tuanbg on 2/9/17.
 */
public class User extends BaseObservable implements Parcelable, Cloneable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private final static int MALE = 0;
    private final static int FEMALE = 1;
    private final static int OTHER = 2;

    @SerializedName("name")
    private String mUsername;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("gender")
    private int mGender;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("password_confirmation")
    private String mConfirmPassword;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("chatwork_id")
    private String mChatWorkId;
    @SerializedName("token_verification")
    private String mToken;
    @SerializedName("is_active")
    private boolean mIsActive;
    @SerializedName("created_at")
    private String mCreateAt;

    public User() {
    }

    protected User(Parcel in) {
        mUsername = in.readString();
        mEmail = in.readString();
        mGender = in.readInt();
        mPassword = in.readString();
        mConfirmPassword = in.readString();
        mAvatar = in.readString();
        mChatWorkId = in.readString();
        mToken = in.readString();
        mIsActive = in.readByte() != 0;
    }

    @Bindable
    public String getCreateAt() {
        return mCreateAt;
    }

    public void setCreateAt(String createAt) {
        mCreateAt = createAt;
        notifyPropertyChanged(BR.createAt);
    }

    @Bindable
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        mConfirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
        notifyPropertyChanged(BR.token);
    }

    @Bindable
    public String getChatWorkId() {
        return mChatWorkId;
    }

    public void setChatWorkId(String chatWorkId) {
        mChatWorkId = chatWorkId;
        notifyPropertyChanged(BR.chatWorkId);
    }

    @Bindable
    public boolean isActive() {
        return mIsActive;
    }

    public void setActive(boolean active) {
        mIsActive = active;
        notifyPropertyChanged(BR.active);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUsername);
        dest.writeString(mEmail);
        dest.writeInt(mGender);
        dest.writeString(mPassword);
        dest.writeString(mConfirmPassword);
        dest.writeString(mAvatar);
        dest.writeString(mChatWorkId);
        dest.writeString(mToken);
        dest.writeByte((byte) (mIsActive ? 1 : 0));
    }

    public int getDisplayGender() {
        switch (mGender) {
            case MALE:
                return R.string.title_male;
            case FEMALE:
                return R.string.title_female;
            case OTHER:
            default:
                return R.string.title_other;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
