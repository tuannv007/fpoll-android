package com.framgia.fpoll.ui.pollcreation.participant;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantPresenter implements ParticipantPollContract.Presenter {
    private ParticipantPollContract.View mView;

    public ParticipantPresenter(ParticipantPollContract.View view) {
        mView = view;
    }

    @Override
    public void nextStep() {
        // TODO: 2/27/17 get response from server, if success change fragment poll created else
        // show error
        if (mView != null) mView.nextStep();
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
