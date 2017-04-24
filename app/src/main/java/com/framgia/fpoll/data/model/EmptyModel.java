package com.framgia.fpoll.data.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.fpoll.FPollApplication;
import com.framgia.fpoll.R;

/**
 * Created by framgia on 20/04/2017.
 */

public class EmptyModel extends BaseObservable {

    private int mImage;
    private String mTitle;
    private String mSubTitle;
    private String mActionTitle;
    private OnActionClickListenner mListenner;
    private int mVisibility;

    public EmptyModel(int visibility) {
        mVisibility = visibility;
    }

    public EmptyModel(State state, int visibility, OnActionClickListenner listenner) {
        Context context = FPollApplication.getContext();
        switch (state) {
            case NO_LOGIN:
                mImage = R.mipmap.ic_launcher;
                mTitle = context.getString(R.string.title_no_login);
                mSubTitle = context.getString(R.string.msg_no_login_subttile);
                mActionTitle = context.getString(R.string.action_login);
                break;
            case NO_INTERNET:
                mImage = R.mipmap.ic_launcher;
                mTitle = context.getString(R.string.title_no_internet);
                mSubTitle = context.getString(R.string.msg_no_internet);
                mActionTitle = null;
                break;
            case NO_POLL:
                mImage = R.mipmap.ic_launcher;
                mTitle = context.getString(R.string.title_no_poll);
                mSubTitle = context.getString(R.string.msg_no_poll);
                mActionTitle = null;
                break;
            default:
                break;
        }
        mListenner = listenner;
        mVisibility = visibility;
    }

    @Bindable
    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Bindable
    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    @Bindable
    public String getActionTitle() {
        return mActionTitle;
    }

    public void setActionTitle(String actionTitle) {
        mActionTitle = actionTitle;
    }

    @Bindable
    public OnActionClickListenner getListenner() {
        return mListenner;
    }

    public void setListenner(OnActionClickListenner listenner) {
        mListenner = listenner;
    }

    @Bindable
    public int getVisibility() {
        return mVisibility;
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    public interface OnActionClickListenner {
        void onActionClick();
    }

    public enum State {
        NO_LOGIN, NO_INTERNET, NO_POLL
    }
}
