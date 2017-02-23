package com.framgia.fpoll.ui.pollparticipant;

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
        if (mView != null) mView.nextStep();
    }

    @Override
    public void previousStep() {
        if (mView != null) mView.previousStep();
    }
}
