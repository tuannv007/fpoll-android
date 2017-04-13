package com.framgia.fpoll.data.source.remote.settings;

import android.content.Context;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 3/23/17.
 */
public class SettingRepository implements SettingDataSource {
    private static SettingRepository sRepository;
    private SettingRemoteDataSource mDataSource;

    private SettingRepository(SettingRemoteDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static SettingRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new SettingRepository(SettingRemoteDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void changeLanguage(String lang, final DataCallback callback) {
        if (callback == null) return;
        mDataSource.changeLanguage(lang, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
