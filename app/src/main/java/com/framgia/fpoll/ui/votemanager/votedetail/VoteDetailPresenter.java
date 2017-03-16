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
        //Add Header
        listProfiles.add(null);
        //Add list votes = user votes + participant votes
        listProfiles.addAll(option.getVotes());
        listProfiles.addAll(option.getParticipantVotes());
        //Remove anonymous
        for (int i = 1; i < listProfiles.size(); i++) {
            if (listProfiles.get(i).getName().equals(TEXT_NO_NAME)
                && listProfiles.get(i).getEmail() == null) {
                listProfiles.remove(i);
            }
        }
        return listProfiles;
    }
}
