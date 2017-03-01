package com.framgia.fpoll.ui.pollcreation.participant;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantHandler {
    private ParticipantPollContract.Presenter mListener;

    public ParticipantHandler(ParticipantPollContract.Presenter listener) {
        mListener = listener;
    }

    public void nextStep() {
        if (mListener != null) mListener.nextStep();
    }

    public void previousStep() {
        if (mListener != null) mListener.previousStep();
    }
    public void finishCreatePoll(){
        if (mListener != null) mListener.previousStep();
    }
}
