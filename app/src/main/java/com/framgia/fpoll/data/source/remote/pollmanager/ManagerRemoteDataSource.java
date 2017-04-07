package com.framgia.fpoll.data.source.remote.pollmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.PollManagerAPI;
import com.framgia.fpoll.util.ActivityUtil;
import java.util.List;

import static com.framgia.fpoll.util.Constant.DataConstant.STATUS_SUCCESS;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public class ManagerRemoteDataSource implements ManagerDataSource {
    private static ManagerDataSource sDataSource;
    private Context mContext;
    private PollManagerAPI mPollManagerAPI;

    private ManagerRemoteDataSource(Context context) {
        mContext = context;
        mPollManagerAPI = ServiceGenerator.createService(PollManagerAPI.class);
    }

    public static ManagerDataSource getInstance(Context context) {
        if (sDataSource == null) sDataSource = new ManagerRemoteDataSource(context);
        return sDataSource;
    }

    @Override
    public void switchPollStatus(String id, @NonNull final DataCallback<String> callback) {
        ServiceGenerator.createService(PollManagerAPI.class)
                .switchPollStatus(id)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void deleteVoting(@NonNull String token, @NonNull final DataCallback<String> callback) {
        mPollManagerAPI.deleteVoting(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem>() {
                            @Override
                            public void onResponse(ResponseItem data) {
                                callback.onSuccess(ActivityUtil.byString(data.getMessage()));
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getHistory(@NonNull String token,
            @NonNull final DataCallback<List<HistoryPoll>> callback) {
        if (mPollManagerAPI == null) return;
        mPollManagerAPI.getHistory(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<List<HistoryPoll>>>() {
                            @Override
                            public void onResponse(ResponseItem<List<HistoryPoll>> data) {
                                if (data == null) return;
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data.getData());
                                } else {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getPollClosed(@NonNull String token,
            @NonNull final DataCallback<List<HistoryPoll>> callback) {
        if (mPollManagerAPI == null) return;
        mPollManagerAPI.getPollClosed(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<List<HistoryPoll>>>() {
                            @Override
                            public void onResponse(ResponseItem<List<HistoryPoll>> data) {
                                if (data == null) return;
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data.getData());
                                } else {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getPollParticipated(@NonNull String token,
            @NonNull final DataCallback<List<HistoryPoll>> callback) {
        if (mPollManagerAPI == null) return;
        mPollManagerAPI.getPollParticipated(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<List<HistoryPoll>>>() {
                            @Override
                            public void onResponse(ResponseItem<List<HistoryPoll>> data) {
                                if (data == null) return;
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data.getData());
                                } else {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void updateLinkPoll(@NonNull String token, @NonNull String oldUser,
            @NonNull String oldAdmin, @NonNull String newUser, @NonNull String newAdmin,
            @NonNull final DataCallback<String> callback) {
        if (mPollManagerAPI == null) return;
        mPollManagerAPI.updateLinkPoll(token, oldUser, oldAdmin, newUser, newAdmin)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<List<String>>>() {
                            @Override
                            public void onResponse(ResponseItem<List<String>> data) {
                                if (data == null) return;
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(ActivityUtil.byString(data.getData()));
                                } else {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getPoll(@NonNull String token, @NonNull final DataCallback<DataInfoItem> callback) {
        if (mPollManagerAPI == null) return;
        mPollManagerAPI.getPoll(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<DataInfoItem>>() {
                            @Override
                            public void onResponse(ResponseItem<DataInfoItem> data) {
                                if (data == null) return;
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data.getData());
                                } else {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getPollDetail(@NonNull String token,
            @NonNull final DataCallback<DataInfoItem> callback) {
        if (mPollManagerAPI == null) return;
        mPollManagerAPI.getPollDetail(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<DataInfoItem>>() {
                            @Override
                            public void onResponse(ResponseItem<DataInfoItem> data) {
                                if (data == null) return;
                                if (data.getStatus() == STATUS_SUCCESS) {
                                    callback.onSuccess(data.getData());
                                } else {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }
}
