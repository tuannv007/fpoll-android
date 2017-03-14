package com.framgia.fpoll.data.source.remote.resetpassword;

import android.content.Context;

import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 3/14/17.
 */
public class ResetDataRepository implements ResetDataSource {
    private ResetRemoteDataSource mResetDataSource;
    private static ResetDataRepository sPollInfoRepository;

    public ResetDataRepository(ResetRemoteDataSource resetDataSource) {
        mResetDataSource = resetDataSource;
    }

    public static ResetDataRepository getInstance(Context context) {
        if (sPollInfoRepository == null) {
            sPollInfoRepository =
                new ResetDataRepository(ResetRemoteDataSource.getInstance(context));
        }
        return sPollInfoRepository;
    }

    @Override
    public void resetPassword(User user, final DataCallback callback) {
        if (callback == null) return;
        mResetDataSource.resetPassword(user, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
