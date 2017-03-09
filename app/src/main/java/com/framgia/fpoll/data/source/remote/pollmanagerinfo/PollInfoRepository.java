package com.framgia.fpoll.data.source.remote.pollmanagerinfo;

import android.content.Context;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 3/6/17.
 */
public class PollInfoRepository implements PollInfoDataSource {
    private PollInfoDataSource mPollInfoDataSource;
    private static PollInfoRepository sPollInfoRepository;

    public PollInfoRepository(PollInfoRemoteDataSource pollDataSource) {
        mPollInfoDataSource = pollDataSource;
    }

    public static PollInfoRepository getInstance(Context context) {
        if (sPollInfoRepository == null) {
            sPollInfoRepository =
                new PollInfoRepository(new PollInfoRemoteDataSource(context));
        }
        return sPollInfoRepository;
    }

    @Override
    public void loadData(String token, final DataCallback callback) {
        if (callback == null) return;
        mPollInfoDataSource.loadData(token, new DataCallback<ResponseItem<DataInfoItem>>() {
            @Override
            public void onSuccess(ResponseItem<DataInfoItem> pollInfoList) {
                callback.onSuccess(pollInfoList);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
