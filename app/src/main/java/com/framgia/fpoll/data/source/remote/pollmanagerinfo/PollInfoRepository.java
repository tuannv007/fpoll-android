package com.framgia.fpoll.data.source.remote.pollmanagerinfo;

import android.content.Context;

import com.framgia.fpoll.data.source.DataCallback;

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
        mPollInfoDataSource.loadData(token, new DataCallback<ItemPollManager.PollInfo>() {
            @Override
            public void onSuccess(ItemPollManager.PollInfo pollInfoList) {
                callback.onSuccess(pollInfoList);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
