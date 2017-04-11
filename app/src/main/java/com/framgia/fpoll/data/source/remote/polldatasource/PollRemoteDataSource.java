package com.framgia.fpoll.data.source.remote.polldatasource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.PollCreationApi;
import com.framgia.fpoll.networking.api.PollEditionApi;
import com.framgia.fpoll.networking.api.PollManagerAPI;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by tuanbg on 3/21/17.
 */
public class PollRemoteDataSource implements PollDataSource {
    private static PollRemoteDataSource sPollRemoteDataSource;
    private Context mContext;

    public PollRemoteDataSource(Context context) {
        mContext = context;
    }

    public static PollRemoteDataSource getInstance(Context context) {
        if (sPollRemoteDataSource == null) {
            sPollRemoteDataSource = new PollRemoteDataSource(context);
        }
        return sPollRemoteDataSource;
    }

    @Override
    public void editPollInformation(int pollId, UpdateInfoPollService.PollInfoBody item,
            @NonNull final DataCallback<DataInfoItem> callback) {
        UpdateInfoPollService service = ServiceGenerator.createService(UpdateInfoPollService.class);
        service.updateInfo(pollId, item)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<DataInfoItem>>() {
                            @Override
                            public void onResponse(ResponseItem<DataInfoItem> data) {
                                if (data == null) {
                                    callback.onError(
                                            mContext.getString(R.string.msg_create_poll_error));
                                } else if (data.getData() == null) {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                } else {
                                    callback.onSuccess(data.getData());
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void createPoll(PollItem pollItem, @NonNull final DataCallback<HistoryPoll> callback) {
        ServiceGenerator.createService(PollCreationApi.PollService.class)
                .createPoll(PollCreationApi.getRequestBody(pollItem))
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<HistoryPoll>>() {
                            @Override
                            public void onResponse(ResponseItem<HistoryPoll> data) {
                                if (data == null) {
                                    callback.onError(
                                            mContext.getString(R.string.msg_create_poll_error));
                                } else if (data.getData() == null) {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                } else {
                                    callback.onSuccess(data.getData());
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void editPoll(int typeEdit, PollItem pollItem,
            @NonNull final DataCallback<DataInfoItem> callback) {
        ServiceGenerator.createService(PollEditionApi.PollEditionService.class)
                .updatePoll(pollItem.getId(), PollEditionApi.getRequestBodyEdit(typeEdit, pollItem))
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<DataInfoItem>>() {
                            @Override
                            public void onResponse(ResponseItem<DataInfoItem> data) {
                                if (data == null) {
                                    callback.onError(
                                            mContext.getString(R.string.msg_create_poll_error));
                                } else if (data.getData() == null) {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                } else {
                                    callback.onSuccess(data.getData());
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }

    @Override
    public void getActivity(String token, @NonNull final DataCallback<DataInfoItem> callback) {
        ServiceGenerator.createService(PollManagerAPI.class)
                .getActivity(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<DataInfoItem>>() {
                            @Override
                            public void onResponse(ResponseItem<DataInfoItem> data) {
                                if (data == null) {
                                    callback.onError(
                                            mContext.getString(R.string.msg_create_poll_error));
                                } else if (data.getData() == null) {
                                    callback.onError(ActivityUtil.byString(data.getMessage()));
                                } else {
                                    callback.onSuccess(data.getData());
                                }
                            }

                            @Override
                            public void onFailure(String message) {
                                callback.onError(message);
                            }
                        }));
    }
}
