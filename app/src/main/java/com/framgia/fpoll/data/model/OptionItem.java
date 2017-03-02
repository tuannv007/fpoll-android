package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.io.Serializable;

/**
 * Created by Nhahv0902 on 3/1/2017.
 * <></>
 */
public class OptionItem extends BaseObservable implements Serializable {
    private String mTitle;
    private String mPathImage;

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
        notifyPropertyChanged(BR.pathImage);
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    @Bindable
    public String getPathImage() {
        return mPathImage;
    }
}
