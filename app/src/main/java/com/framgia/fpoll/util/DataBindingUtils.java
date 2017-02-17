package com.framgia.fpoll.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.framgia.fpoll.R;

/**
 * Created by Nhahv0902 on 2/9/2017.
 * <></>
 */
public class DataBindingUtils {
    @BindingAdapter({"bind:imageResource"})
    public static void loadImage(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter({"bind:imagePath"})
    public static void loadImagePath(ImageView view, String path) {
        Glide.with(view.getContext())
            .load(path)
            .placeholder(R.drawable.ic_insert_photo_black_24px)
            .into(view);
    }

    @BindingAdapter("bind:background")
    public static void setCardBackground(CardView view, int color) {
        view.setCardBackgroundColor(color);
    }
}
