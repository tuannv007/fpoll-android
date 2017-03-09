package com.framgia.fpoll.data.source.remote.pollmanagerinfo;

import android.content.Context;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.PollInfoApi;

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
        getData.getPollInfo(token).enqueue(new CallbackManager<>(mContext,
            new CallbackManager.CallBack<ResponseItem<DataInfoItem>>() {
                @Override
                public void onResponse(ResponseItem<DataInfoItem> data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
