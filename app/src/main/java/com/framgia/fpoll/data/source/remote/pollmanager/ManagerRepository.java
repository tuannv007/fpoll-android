package com.framgia.fpoll.data.source.remote.pollmanager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;

import java.util.List;

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

    @Override
    public void deleteVoting(@NonNull String token, @NonNull final DataCallback<String> callback) {
        mDataSource.deleteVoting(token, new DataCallback<String>() {
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

    @Override
    public void getHistory(@NonNull String token,
                           @NonNull final DataCallback<List<HistoryPoll>> callback) {
        if (mDataSource == null) return;
        mDataSource.getHistory(token, new DataCallback<List<HistoryPoll>>() {
            @Override
            public void onSuccess(List<HistoryPoll> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void getPollClosed(@NonNull String token,
                              @NonNull final DataCallback<List<HistoryPoll>> callback) {
        if (mDataSource == null) return;
        mDataSource.getHistory(token, new DataCallback<List<HistoryPoll>>() {
            @Override
            public void onSuccess(List<HistoryPoll> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void getPollParticipated(@NonNull String token,
                                    @NonNull final DataCallback<List<HistoryPoll>> callback) {
        if (mDataSource == null) return;
        mDataSource.getPollParticipated(token, new DataCallback<List<HistoryPoll>>() {
            @Override
            public void onSuccess(List<HistoryPoll> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void updateLinkPoll(@NonNull String token, @NonNull String oldUser,
                               @NonNull String oldAdmin, @NonNull String newUser,
                               @NonNull String newAdmin,
                               @NonNull final DataCallback<String> callback) {
        if (mDataSource == null) return;
        mDataSource.updateLinkPoll(token, oldUser, oldAdmin, newUser, newAdmin,
            new DataCallback<String>() {
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
