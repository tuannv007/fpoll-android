package com.framgia.fpoll.ui.votemanager.votedetail;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anhtv on 16/03/2017.
 */
public class VoteDetailPresenter implements VoteDetailContract.Presenter {
    private static final String TEXT_NO_NAME = "No Name";

    @Override
    public List<ParticipantVotes> getListProfile(Option option) {
        List<ParticipantVotes> listProfiles = new ArrayList<>();
        //Add list participant
        listProfiles.addAll(option.getParticipantVotes());
        //Remove anonymous
        for (int i = 0; i < listProfiles.size(); i++) {
            if (listProfiles.get(i).getName().equals(TEXT_NO_NAME)
                && listProfiles.get(i).getEmail() == null) {
                listProfiles.remove(i);
                i--;
            }
        }
        //Add list user votes
        listProfiles.addAll(option.getVotes());
        //Add Header
        listProfiles.add(0, null);
        return listProfiles;
    }
}
