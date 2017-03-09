package com.framgia.fpoll.data.source.remote.voteinfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;

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
    public void getVoteInfo(String token,@NonNull final DataCallback<VoteInfo> callback) {
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
}
