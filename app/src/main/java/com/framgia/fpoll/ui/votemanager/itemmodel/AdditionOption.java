package com.framgia.fpoll.ui.votemanager.itemmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;

import com.framgia.fpoll.BR;

/**
 * Created by anhtv on 17/03/2017.
 */
public class AdditionOption extends BaseObservable {
    private String mOptionText;
    private Uri mImageUri;

    @Bindable
    public String getOptionText() {
        return mOptionText;
    }

    public void setOptionText(String optionText) {
        mOptionText = optionText;
        notifyPropertyChanged(BR.optionText);
    }

    @Bindable
    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri imageUri) {
        mImageUri = imageUri;
        notifyPropertyChanged(BR.imageUri);
    }
}
