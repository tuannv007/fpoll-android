package com.framgia.fpoll.data.source.remote.polldatasource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.UpdatePollService;
import java.util.List;

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
    public void updateInformation(int pollId, UpdatePollService.PollInfoBody body,
            @NonNull final DataCallback<PollItem> callback) {
        mRemoteDataSource.updateInformation(pollId, body, new DataCallback<PollItem>() {
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
    public void updateOptionSetting(int editType, PollItem pollItem,
            @NonNull final DataCallback<PollItem> callback) {
        mRemoteDataSource.updateOptionSetting(editType, pollItem, new DataCallback<PollItem>() {
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

    @Override
    public void updateOption(int id, @NonNull List<Option> options,
            @NonNull final DataCallback<PollItem> callback) {
        if (mRemoteDataSource == null) return;
        mRemoteDataSource.updateOption(id, options, new DataCallback<PollItem>() {
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
