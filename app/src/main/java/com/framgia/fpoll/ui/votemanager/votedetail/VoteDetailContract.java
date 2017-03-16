package com.framgia.fpoll.ui.votemanager.votedetail;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;

import java.util.List;

/**
 * Created by anhtv on 16/03/2017.
 */
public interface VoteDetailContract {
    interface Presenter {
        List<ParticipantVotes> getListProfile(Option option);
    }
}
