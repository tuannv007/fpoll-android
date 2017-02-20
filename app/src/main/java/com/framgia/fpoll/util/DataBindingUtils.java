package com.framgia.fpoll.util;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

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

    @BindingAdapter("bind:imageUrl")
    public void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter(value = {"bind:selectedValue", "bind:selectedValueAttrChanged"},
        requireAll = false)
    public static void bindSpinnerData(Spinner spinner, int selectedPosition, final
    InverseBindingListener newTextAttrChanged) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (selectedPosition != -1) {
            spinner.setSelection(selectedPosition, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue",
        event = "bind:selectedValueAttrChanged")
    public static int captureSelectedValue(Spinner spinner) {
        return spinner.getSelectedItemPosition();
    }
}
