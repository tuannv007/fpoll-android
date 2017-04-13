package com.framgia.fpoll.data.source.remote.resentemail;

import android.content.Context;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 3/15/17.
 */
public class ResentEmailRepository implements ResentEmailDataSource {
    public static ResentEmailRepository sEmailRepository;
    public ResentEmailRemoteDataSource mRemoteDataSource;

    public ResentEmailRepository(ResentEmailRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static ResentEmailRepository getInstance(Context context) {
        if (sEmailRepository == null) {
            sEmailRepository =
                    new ResentEmailRepository(ResentEmailRemoteDataSource.getInstance(context));
        }
        return sEmailRepository;
    }

    @Override
    public void resentEmail(int pollId, final DataCallback callback) {
        if (callback == null) return;
        mRemoteDataSource.resentEmail(pollId, new DataCallback<ResponseItem>() {
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
