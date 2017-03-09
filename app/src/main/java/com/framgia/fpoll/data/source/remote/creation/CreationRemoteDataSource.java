package com.framgia.fpoll.data.source.remote.creation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.PollCreationApi;

/**
 * Created by framgia on 06/03/2017.
 */
public class CreationRemoteDataSource implements CreationDataSource {
    private static CreationRemoteDataSource sRemoteDataSource;
    private Context mContext;

    private CreationRemoteDataSource(Context context) {
        mContext = context;
    }

    public static CreationRemoteDataSource getInstance(Context context) {
        if (sRemoteDataSource == null) sRemoteDataSource = new CreationRemoteDataSource(context);
        return sRemoteDataSource;
    }

    @Override
    public void createPoll(PollItem pollItem, @NonNull final DataCallback<PollItem> callback) {
        ServiceGenerator.createService(PollCreationApi.PollService.class)
            .createPoll(PollCreationApi.getRequestBody(pollItem))
            .enqueue(new CallbackManager<>(mContext,
                new CallbackManager.CallBack<ResponseItem<PollItem>>() {
                    @Override
                    public void onResponse(ResponseItem<PollItem> data) {
                        if (data == null) {
                            callback.onError(mContext.getString(R.string.msg_create_poll_error));
                        } else callback.onSuccess(data.getData());
                    }

                    @Override
                    public void onFailure(String message) {
                        callback.onError(message);
                    }
                }));
    }
}
