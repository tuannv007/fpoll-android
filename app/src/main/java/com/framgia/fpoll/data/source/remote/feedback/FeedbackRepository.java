package com.framgia.fpoll.data.source.remote.feedback;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class FeedbackRepository implements FeedbackDataSource {
    private static FeedbackRepository sRepository;
    private FeedbackDataSource mDataSource;

    private FeedbackRepository(Context context) {
        mDataSource = FeedbackRemoteDataSource.getInstance(context);
    }

    public static FeedbackRepository getInstance(Context context) {
        if (sRepository == null) sRepository = new FeedbackRepository(context);
        return sRepository;
    }

    @Override
    public void sendFeedback(String name, String email, String content,
            @NonNull final DataCallback<String> callback) {
        mDataSource.sendFeedback(name, email, content, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
