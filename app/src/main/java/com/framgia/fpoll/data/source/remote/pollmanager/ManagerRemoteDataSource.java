package com.framgia.fpoll.data.source.remote.pollmanager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.PollManagerAPI;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class ManagerRemoteDataSource implements ManagerDataSource {
    private static ManagerDataSource sDataSource;
    private Context mContext;
    private PollManagerAPI mPollManagerAPI;

    private ManagerRemoteDataSource(Context context) {
        mContext = context;
        mPollManagerAPI = ServiceGenerator.createService(PollManagerAPI.class);
    }

    public static ManagerDataSource getInstance(Context context) {
        if (sDataSource == null) sDataSource = new ManagerRemoteDataSource(context);
        return sDataSource;
    }

    @Override
    public void switchPollStatus(String id, @NonNull final DataCallback<String> callback) {
        ServiceGenerator.createService(PollManagerAPI.class)
            .switchPollStatus(id)
            .enqueue(new CallbackManager<>(mContext,
                new CallbackManager.CallBack<ResponseItem>() {
                    @Override
                    public void onResponse(ResponseItem data) {
                        callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                    }

                    @Override
                    public void onFailure(String message) {
                        callback.onError(message);
                    }
                }));
    }

    @Override
    public void deleteVoting(@NonNull String token, @NonNull final DataCallback<String> callback) {
        mPollManagerAPI.deleteVoting(token).enqueue(
            new CallbackManager<>(mContext, new CallbackManager.CallBack<ResponseItem>() {
                @Override
                public void onResponse(ResponseItem data) {
                    callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
