package com.framgia.fpoll.data.source.remote.pollmanager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class ManagerRepository implements ManagerDataSource {
    private static ManagerRepository sRepository;
    private ManagerDataSource mDataSource;

    public ManagerRepository(Context context) {
        mDataSource = ManagerRemoteDataSource.getInstance(context);
    }

    public static ManagerRepository getInstance(Context context) {
        if (sRepository == null) sRepository = new ManagerRepository(context);
        return sRepository;
    }

    @Override
    public void switchPollStatus(String id, @NonNull final DataCallback<String> callback) {
        mDataSource.switchPollStatus(id, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
