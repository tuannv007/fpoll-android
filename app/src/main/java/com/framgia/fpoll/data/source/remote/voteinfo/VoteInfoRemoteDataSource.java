package com.framgia.fpoll.data.source.remote.voteinfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.VoteInfoAPI;

import java.util.List;
import java.util.Objects;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anhtv on 07/03/2017.
 */
public class VoteInfoRemoteDataSource implements VoteInfoDataSource {
    private static VoteInfoRemoteDataSource sVoteInfoRemoteDataSource;
    private Context mContext;

    private VoteInfoRemoteDataSource(Context context) {
        mContext = context;
    }

    public static VoteInfoRemoteDataSource getInstance(Context context) {
        if (sVoteInfoRemoteDataSource == null) sVoteInfoRemoteDataSource = new
            VoteInfoRemoteDataSource(context);
        return sVoteInfoRemoteDataSource;
    }

    @Override
    public void getVoteInfo(String token, @NonNull final DataCallback<VoteInfo> callback) {
        ServiceGenerator.createService(VoteInfoAPI.class).showVoteInfo(token).enqueue(
            new CallbackManager<>(mContext, new CallbackManager.CallBack<ResponseItem<VoteInfo>>() {
                @Override
                public void onResponse(ResponseItem<VoteInfo> data) {
                    if (data != null) callback.onSuccess(data.getData());
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
        ServiceGenerator.createService(VoteInfoAPI.class).postComment(comment)
            .enqueue(new CallbackManager<>(mContext,
                new CallbackManager.CallBack<ResponseItem<FpollComment>>() {
                    @Override
                    public void onResponse(ResponseItem<FpollComment> data) {
                        if (data != null) callback.onSuccess(data.getData());
                    }

                    @Override
                    public void onFailure(String message) {
                        callback.onError(message);
                    }
                }));
    }

    @Override
    public void votePoll(VoteInfoAPI.OptionsBody optionsBody,
                         final DataCallback<List<String>> callback) {
        ServiceGenerator.createService(VoteInfoAPI.class).votePoll(optionsBody.getRequestBody())
            .enqueue(new CallbackManager<>(mContext,
                new CallbackManager.CallBack<ResponseItem<Object>>() {
                    @Override
                    public void onResponse(ResponseItem<Object> data) {
                        callback.onSuccess(data.getMessage());
                    }

                    @Override
                    public void onFailure(String message) {
                        callback.onError(message);
                    }
                }));
    }
}
