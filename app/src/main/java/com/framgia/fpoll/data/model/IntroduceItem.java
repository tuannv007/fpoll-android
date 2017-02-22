package com.framgia.fpoll.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceItem extends BaseObservable {
    private int mImage;
    private String mTitle;

    @Bindable
    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = image;
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public IntroduceItem(String title, int image) {
        this.mImage = image;
        this.mTitle = title;
    }

    @Override
    public String toString() {
        return "IntroduceItem{" +
            "mImage='" + mImage + '\'' +
            ", mTitle='" + mTitle + '\'' +
            '}';
    }
}
