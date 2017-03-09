package com.framgia.fpoll.ui.pollcreation.participant;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.creation.CreationRepository;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantPresenter implements ParticipantPollContract.Presenter {
    private ParticipantPollContract.View mView;
    private CreationRepository mCreationRepository;
    private PollItem mPollItem;

    public ParticipantPresenter(ParticipantPollContract.View view,
                                CreationRepository creationRepository, PollItem pollItem) {
        mView = view;
        mCreationRepository = creationRepository;
        mPollItem = pollItem;
    }

    @Override
    public void nextStep() {
        if (mView != null) mView.showDialog();
        mCreationRepository.createPoll(mPollItem, new DataCallback() {
            @Override
            public void onSuccess(Object data) {
                if (mView != null) {
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
}
