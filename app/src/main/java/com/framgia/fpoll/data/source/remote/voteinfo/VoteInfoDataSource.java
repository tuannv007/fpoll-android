package com.framgia.fpoll.data.source.remote.voteinfo;

import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.VoteInfoAPI;

/**
 * Created by anhtv on 07/03/2017.
 */
public interface VoteInfoDataSource {
    void getVoteInfo(String token, DataCallback<VoteInfo> getVoteInfoCallback);

    void postComment(VoteInfoAPI.CommentBody comment, DataCallback<FpollComment> callback);

    void votePoll(VoteInfoAPI.OptionsBody optionsBody, DataCallback<ParticipantVotes> callback);

    void getVoteResult(@NonNull String token, @NonNull DataCallback<ResultVoteItem> callback);

    void getVoteDetail(@NonNull String token, @NonNull DataCallback<VoteDetail> callback);
}
