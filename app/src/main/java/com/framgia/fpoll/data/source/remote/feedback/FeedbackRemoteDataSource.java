package com.framgia.fpoll.data.source.remote.feedback;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.FeedbackAPI;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public class FeedbackRemoteDataSource implements FeedbackDataSource {
    private static FeedbackDataSource sDataSource;
    private Context mContext;

    private FeedbackRemoteDataSource(Context context) {
        mContext = context;
    }

    public static FeedbackDataSource getInstance(Context context) {
        if (sDataSource == null) sDataSource = new FeedbackRemoteDataSource(context);
        return sDataSource;
    }

    @Override
    public void sendFeedback(String name, String email, String content,
            @NonNull final DataCallback<String> callback) {
        FeedbackAPI.FeedbackBody body = new FeedbackAPI.FeedbackBody(name, email, content);
        ServiceGenerator.createService(FeedbackAPI.class)
                .feedback(body)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                if (data != null && data.getMessage() != null) {
                                    callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                                } else {
                                    callback.onError(
                                            mContext.getString(R.string.msg_send_feedback_error));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }
}
