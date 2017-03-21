package com.framgia.fpoll.data.source.remote.voteinfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.VoteInfoAPI;

import java.util.List;

/**
 * Created by anhtv on 07/03/2017.
 */
public class VoteInfoRepository implements VoteInfoDataSource {
    private static VoteInfoRepository sVoteInfoRepository;
    private VoteInfoDataSource mVoteInfoRemoteSource;

    public VoteInfoRepository(@NonNull VoteInfoDataSource voteInfoRemoteSource) {
        mVoteInfoRemoteSource = voteInfoRemoteSource;
    }

    public static VoteInfoRepository getInstance(Context context) {
        if (sVoteInfoRepository == null) {
            sVoteInfoRepository =
                new VoteInfoRepository(VoteInfoRemoteDataSource.getInstance(context));
        }
        return sVoteInfoRepository;
    }

    @Override
    public void getVoteInfo(String token, @NonNull final DataCallback<VoteInfo> callback) {
        mVoteInfoRemoteSource.getVoteInfo(token, new DataCallback<VoteInfo>() {
            @Override
            public void onSuccess(VoteInfo data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void postComment(VoteInfoAPI.CommentBody comment,
                            final DataCallback<FpollComment> callback) {
        mVoteInfoRemoteSource.postComment(comment, new DataCallback<FpollComment>() {
            @Override
            public void onSuccess(FpollComment data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void votePoll(VoteInfoAPI.OptionsBody optionsBody,
                         final DataCallback<ParticipantVotes> callback) {
        mVoteInfoRemoteSource.votePoll(optionsBody, new DataCallback<ParticipantVotes>() {
            @Override
            public void onSuccess(ParticipantVotes data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void updateNewOption(int pollId, VoteInfoAPI.NewOptionBody newOptionBody,
                                final DataCallback<Poll> callback) {
        mVoteInfoRemoteSource.updateNewOption(pollId, newOptionBody, new DataCallback<Poll>() {
            @Override
            public void onSuccess(Poll data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
