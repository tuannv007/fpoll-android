package com.framgia.fpoll.data.source.remote.voteinfo;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.VoteInfoAPI;

/**
 * Created by anhtv on 07/03/2017.
 */
public interface VoteInfoDataSource {
    void getVoteInfo(String token, DataCallback<VoteInfo> getVoteInfoCallback);
    void postComment(VoteInfoAPI.CommentBody comment, DataCallback<FpollComment> callback);
}
