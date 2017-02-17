package com.framgia.fpoll.ui.authenication.register;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by tuanbg on 2/17/17.
 */
public class RegisterItemActionHandle {
    private RegisterContract.Presenter mListener;

    public RegisterItemActionHandle(RegisterContract.Presenter listener) {
        mListener = listener;
    }

    public void submitAccount() {
        mListener.registerUser();
    }

    public void openGallery() {
        mListener.openGallery();
    }

    @BindingAdapter("bind:imageUri")
    public  void setImageUrl(ImageView imageView, String uri) {
        Context context = imageView.getContext();
        Glide.with(context).load(uri).into(imageView);
    }
}
