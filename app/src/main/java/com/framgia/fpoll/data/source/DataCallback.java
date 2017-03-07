package com.framgia.fpoll.data.source;

public interface DataCallback<T> {
    void onSuccess(T data);
    void onError(String msg);
}
