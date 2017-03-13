package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;
import com.framgia.fpoll.data.model.poll.Option;

/**
 * Created by anhtv on 10/03/2017.
 */
public class OptionModel extends BaseObservable {
    private Option mOption;
    private boolean mIsChecked;

    public OptionModel() {
    }

    public OptionModel(Option option, boolean isChecked) {
        mOption = option;
        mIsChecked = isChecked;
    }

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
}
