package com.framgia.fpoll.data.source.remote.feedback;

import android.support.annotation.NonNull;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public interface FeedbackDataSource {
    void sendFeedback(String name, String email, String content,
            @NonNull DataCallback<String> callback);
}
