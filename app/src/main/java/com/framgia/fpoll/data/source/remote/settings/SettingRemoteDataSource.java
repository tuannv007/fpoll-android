package com.framgia.fpoll.data.source.remote.settings;

import android.content.Context;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.SettingService;

/**
 * Created by tuanbg on 3/23/17.
 */
public class SettingRemoteDataSource implements SettingDataSource {
    private static SettingRemoteDataSource sRemoteDataSource;
    private Context mContext;

    public SettingRemoteDataSource(Context context) {
        mContext = context;
    }

    public static SettingRemoteDataSource getInstance(Context context) {
        if (sRemoteDataSource == null) sRemoteDataSource = new SettingRemoteDataSource(context);
        return sRemoteDataSource;
    }

    @Override
    public void changeLanguage(String lang, final DataCallback callback) {
        if (callback == null) return;
        ServiceGenerator.createService(SettingService.class)
                .changeLanguage(lang)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                if (data == null) return;
                                callback.onSuccess(data);
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }
}
