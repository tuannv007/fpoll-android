package com.framgia.fpoll.data.source.remote.creation;

import android.content.Context;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.ApiRestClient.APIService.pollcreationservice.PollCreationApi;
import com.framgia.fpoll.data.ApiRestClient.APIService.pollcreationservice.PollItem;
import com.framgia.fpoll.data.ApiRestClient.APIService.pollcreationservice.PollResponse;
import com.framgia.fpoll.data.ApiRestClient.CallbackManager;
import com.framgia.fpoll.data.ApiRestClient.ServiceGenerator;
import com.framgia.fpoll.data.source.DataCallback;

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
    public void createPoll(PollItem pollItem, final DataCallback callback) {
        ServiceGenerator.createService(PollCreationApi.PollService.class)
            .createPoll(PollCreationApi.getRequestBody(pollItem))
            .enqueue(new CallbackManager<>(mContext, new CallbackManager.CallBack<PollResponse>() {
                @Override
                public void onResponse(PollResponse data) {
                    if (data == null) {
                        callback.onError(mContext.getString(R.string.msg_create_poll_error));
                    } else callback.onSuccess(data.getPollItem());
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
