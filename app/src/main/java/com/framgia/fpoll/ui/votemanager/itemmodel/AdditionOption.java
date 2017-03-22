package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fpoll.BR;

/**
 * Created by anhtv on 17/03/2017.
 */
public class AdditionOption extends BaseObservable {
    private String mOptionText;
    private String mImagePath;

    public AdditionOption(String optionText, String imagePath) {
        mOptionText = optionText;
        mImagePath = imagePath;
    }

    @Bindable
    public String getOptionText() {
        return mOptionText;
    }

    public void setOptionText(String optionText) {
        mOptionText = optionText;
        notifyPropertyChanged(BR.optionText);
    }

    @Bindable
    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
        notifyPropertyChanged(BR.imagePath);
    }
}
