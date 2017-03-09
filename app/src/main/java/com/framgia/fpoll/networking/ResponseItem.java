package com.framgia.fpoll.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseItem<T> {
    @SerializedName("error")
    private boolean mError;
    @SerializedName("mStatus")
    private int mStatus;
    @SerializedName("data")
    private T mData;
    @SerializedName("messages")
    private List<String> mMessage;

    public int getStatus() {
        return mStatus;
    }

    public boolean isError() {
        return mError;
    }

    public T getData() {
        return mData;
    }

    public List<String> getMessage() {
        return mMessage;
    }
}
