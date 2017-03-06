package com.framgia.fpoll.data.source.remote.feedback;

import android.support.annotation.NonNull;

import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public interface FeedbackDataSource {
    interface Callback {
        void onSuccess(ResponseItem<String> data);
        void onError(String message);
    }
    void sendFeedback(String name, String email, String content, @NonNull Callback callback);
}
