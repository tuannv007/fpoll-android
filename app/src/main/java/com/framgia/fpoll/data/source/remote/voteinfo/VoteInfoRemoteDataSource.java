package com.framgia.fpoll.data.source.remote.voteinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.VoteInfoAPI;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by anhtv on 07/03/2017.
 */
public class VoteInfoRemoteDataSource implements VoteInfoDataSource {
    private static VoteInfoRemoteDataSource sVoteInfoRemoteDataSource;
    private Context mContext;
    private VoteInfoAPI mService;

    private VoteInfoRemoteDataSource(Context context) {
        mContext = context;
        mService = ServiceGenerator.createService(VoteInfoAPI.class);
    }

    public static VoteInfoRemoteDataSource getInstance(Context context) {
        if (sVoteInfoRemoteDataSource == null) {
            sVoteInfoRemoteDataSource = new VoteInfoRemoteDataSource(context);
        }
        return sVoteInfoRemoteDataSource;
    }

    @Override
    public void getVoteInfo(String token, @NonNull final DataCallback<VoteInfo> callback) {
        if (mService == null) return;
        mService.showVoteInfo(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<VoteInfo>>() {
                            @Override
                            public void onResponse(ResponseItem<VoteInfo> data) {
                                if (data != null && data.getData() != null) {
                                    callback.onSuccess(data.getData());
                                } else if (data != null) {
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
    public void getVoteResult(@NonNull String token,
            @NonNull final DataCallback<ResultVoteItem> callback) {
        if (mService == null) return;
        mService.getVoteResult(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<ResultVoteItem>>() {
                            @Override
                            public void onResponse(ResponseItem<ResultVoteItem> data) {
                                if (data != null && data.getData() != null) {
                                    callback.onSuccess(data.getData());
                                } else if (data != null) {
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
    public void getVoteDetail(@NonNull String token,
            @NonNull final DataCallback<VoteDetail> callback) {
        if (mService == null) return;
        mService.getVoteDetail(token)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<VoteDetail>>() {
                            @Override
                            public void onResponse(ResponseItem<VoteDetail> data) {
                                if (data != null && data.getData() != null) {
                                    callback.onSuccess(data.getData());
                                } else if (data != null) {
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
    public void postComment(VoteInfoAPI.CommentBody comment,
            final DataCallback<FpollComment> callback) {
        if (mService == null) return;
        mService.postComment(comment)
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<FpollComment>>() {
                            @Override
                            public void onResponse(ResponseItem<FpollComment> data) {
                                if (data != null && data.getData() != null) {
                                    callback.onSuccess(data.getData());
                                } else if (data != null) {
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
    public void votePoll(VoteInfoAPI.OptionsBody optionsBody,
            final DataCallback<ParticipantVotes> callback) {
        if (mService == null) return;
        mService.votePoll(optionsBody.getRequestBody())
                .enqueue(new CallbackManager<>(mContext,
                        new CallbackManager.CallBack<ResponseItem<ParticipantVotes>>() {
                            @Override
                            public void onResponse(ResponseItem<ParticipantVotes> data) {
                                if (data != null && data.getData() != null) {
                                    callback.onSuccess(data.getData());
                                } else if (data != null) {
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
