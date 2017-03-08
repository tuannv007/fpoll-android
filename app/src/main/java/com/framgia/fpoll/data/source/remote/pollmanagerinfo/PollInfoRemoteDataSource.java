package com.framgia.fpoll.data.source.remote.pollmanagerinfo;

import android.content.Context;

import com.framgia.fpoll.data.ApiRestClient.APIService.pollmanager.PollInfoApi;
import com.framgia.fpoll.data.ApiRestClient.CallbackManager;
import com.framgia.fpoll.data.ApiRestClient.ServiceGenerator;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by tuanbg on 3/6/17.
 */
public class PollInfoRemoteDataSource implements PollInfoDataSource {
    private static PollInfoRemoteDataSource sPollInfoRemoteDataSource;
    private Context mContext;

    public PollInfoRemoteDataSource(Context context) {
        mContext = context;
    }

    public static PollInfoRemoteDataSource getInstance(Context context) {
        if (sPollInfoRemoteDataSource == null) {
            sPollInfoRemoteDataSource = new PollInfoRemoteDataSource(context);
        }
        return sPollInfoRemoteDataSource;
    }

    @Override
    public void loadData(String token, final DataCallback callback) {
        if (callback == null) return;
        PollInfoApi.PollInfoService getData =
            ServiceGenerator.createService(PollInfoApi.PollInfoService.class);
        getData.getPollInfo(token)
            .enqueue(new CallbackManager<>(mContext,
                new CallbackManager.CallBack<ItemPollManager.PollInfo>() {
                    @Override
                    public void onResponse(ItemPollManager.PollInfo data) {
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        callback.onError(message);
                    }
                }));
    }
}
