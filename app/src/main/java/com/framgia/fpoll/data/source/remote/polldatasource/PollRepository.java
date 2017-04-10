package com.framgia.fpoll.data.source.remote.polldatasource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;

/**
 * Created by tuanbg on 3/21/17.
 */
public class PollRepository implements PollDataSource {
    private static PollRepository sPollRepository;
    private PollRemoteDataSource mRemoteDataSource;

    public PollRepository(PollRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static PollRepository getInstance(Context context) {
        if (sPollRepository == null) {
            sPollRepository = new PollRepository(PollRemoteDataSource.getInstance(context));
        }
        return sPollRepository;
    }

    @Override
    public void editPollInformation(int pollId, UpdateInfoPollService.PollInfoBody body,
            @NonNull final DataCallback<DataInfoItem> callback) {
        mRemoteDataSource.editPollInformation(pollId, body, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void createPoll(PollItem pollItem, @NonNull final DataCallback<HistoryPoll> callback) {
        mRemoteDataSource.createPoll(pollItem, new DataCallback<HistoryPoll>() {
            @Override
            public void onSuccess(HistoryPoll data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void editPoll(int typeEdit, PollItem pollItem,
            @NonNull final DataCallback<DataInfoItem> callback) {
        mRemoteDataSource.editPoll(typeEdit, pollItem, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void getActivity(String token, final @NonNull DataCallback<DataInfoItem> callback) {
        if (mRemoteDataSource == null) return;
        mRemoteDataSource.getActivity(token, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
