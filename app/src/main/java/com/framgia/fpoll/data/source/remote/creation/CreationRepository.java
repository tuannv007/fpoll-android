package com.framgia.fpoll.data.source.remote.creation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by framgia on 06/03/2017.
 */
public class CreationRepository implements CreationDataSource {
    private static CreationRepository sRepository;
    private CreationDataSource mDataSource;

    private CreationRepository(CreationDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static CreationRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new CreationRepository(CreationRemoteDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void createPoll(PollItem pollItem, @NonNull final DataCallback<PollItem> callback) {
        mDataSource.createPoll(pollItem, new DataCallback<PollItem>() {
            @Override
            public void onSuccess(PollItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
