package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.Option;

/**
 * Created by anhtv on 10/03/2017.
 */
public class OptionModel extends BaseObservable implements Parcelable {
    private Option mOption;
    private boolean mIsChecked;

    public OptionModel() {
    }

    public OptionModel(Option option, boolean isChecked) {
        mOption = option;
        mIsChecked = isChecked;
    }

    protected OptionModel(Parcel in) {
        mOption = in.readParcelable(Option.class.getClassLoader());
        mIsChecked = in.readByte() != 0;
    }

    public static final Creator<OptionModel> CREATOR = new Creator<OptionModel>() {
        @Override
        public OptionModel createFromParcel(Parcel in) {
            return new OptionModel(in);
        }

        @Override
        public OptionModel[] newArray(int size) {
            return new OptionModel[size];
        }
    };

    @Bindable
    public Option getOption() {
        return mOption;
    }

    public void setOption(Option option) {
        mOption = option;
        notifyPropertyChanged(BR.option);
    }

    @Bindable
    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
        notifyPropertyChanged(BR.checked);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mOption, flags);
        dest.writeByte((byte) (mIsChecked ? 1 : 0));
    }
}
