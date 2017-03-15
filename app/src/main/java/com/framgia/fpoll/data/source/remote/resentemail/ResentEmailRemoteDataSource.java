package com.framgia.fpoll.data.source.remote.resentemail;

import android.annotation.SuppressLint;
import android.content.Context;

import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.ResentEmailService;

/**
 * Created by tuanbg on 3/15/17.
 */
public class ResentEmailRemoteDataSource implements ResentEmailDataSource {
    @SuppressLint("StaticFieldLeak")
    private static ResentEmailRemoteDataSource sRemoteDataSource;
    private Context mContext;

    public ResentEmailRemoteDataSource(Context context) {
        mContext = context;
    }

    public static ResentEmailRemoteDataSource getInstance(Context context) {
        if (sRemoteDataSource == null) sRemoteDataSource = new ResentEmailRemoteDataSource(context);
        return sRemoteDataSource;
    }

    @Override
    public void resentEmail(int pollId, final DataCallback callback) {
        if (callback == null) return;
        ResentEmailService getData =
            ServiceGenerator.createService(ResentEmailService.class);
        getData.resentEmail(pollId).enqueue(new CallbackManager<>(mContext,
            new CallbackManager.CallBack<ResponseItem>() {
                @Override
                public void onResponse(ResponseItem data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
