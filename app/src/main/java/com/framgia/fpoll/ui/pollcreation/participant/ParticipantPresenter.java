package com.framgia.fpoll.ui.pollcreation.participant;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantPresenter implements ParticipantPollContract.Presenter {
    private ParticipantPollContract.View mView;
    private PollRepository mCreationRepository;
    private PollItem mPoll;

    public ParticipantPresenter(ParticipantPollContract.View view,
                                PollRepository creationRepository, PollItem poll) {
        mView = view;
        mCreationRepository = creationRepository;
        mPoll = poll;
        mView.start();
    }

    @Override
    public void nextStep() {
        if (mView != null) mView.showDialog();
        mCreationRepository.createPoll(mPoll, new DataCallback<PollItem>() {
            @Override
            public void onSuccess(PollItem data) {
                if (mView != null) {
                    mPoll.setId(data.getId());
                    mPoll.setLink(data.getLink());
                    mView.hideDialog();
                    mView.nextStep();
                }
            }

            @Override
            public void onError(String msg) {
                if (mView != null) {
                    mView.hideDialog();
                    mView.showCreatePollError(msg);
                }
            }
        });
    }

    @Override
    public void previousStep() {
        if (mView != null) mView.previousStep();
    }

    public void getEmail(String textEmail) {
        String[] listEmail = textEmail.split(",");
        for (String email : listEmail) {
            // TODO: 2/27/17 multi email
        }
    }

    public PollItem getPoll() {
        return mPoll;
    }
}
