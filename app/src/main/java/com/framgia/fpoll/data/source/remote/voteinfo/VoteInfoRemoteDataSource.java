package com.framgia.fpoll.data.source.remote.voteinfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.VoteInfoAPI;

import java.util.List;

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
                         final DataCallback<ParticipantVotes> callback) {
        ServiceGenerator.createService(VoteInfoAPI.class).votePoll(optionsBody.getRequestBody())
            .enqueue(new CallbackManager<>(mContext,
                new CallbackManager.CallBack<ResponseItem<ParticipantVotes>>() {
                    @Override
                    public void onResponse(ResponseItem<ParticipantVotes> data) {
                        callback.onSuccess(data.getData());
                    }

                    @Override
                    public void onFailure(String message) {
                        callback.onError(message);
                    }
                }));
    }

    @Override
    public void updateNewOption(int pollId, VoteInfoAPI.NewOptionBody newOptionBody,
                                final DataCallback<Poll> callback) {
        ServiceGenerator.createService(VoteInfoAPI.class)
            .updateOption(pollId, newOptionBody.getRequestBody()).enqueue(new CallbackManager<>(
            mContext, new CallbackManager.CallBack<ResponseItem<Poll>>() {
            @Override
            public void onResponse(ResponseItem<Poll> data) {
                callback.onSuccess(data.getData());
            }

            @Override
            public void onFailure(String message) {
                callback.onError(message);
            }
        }));
    }
}
