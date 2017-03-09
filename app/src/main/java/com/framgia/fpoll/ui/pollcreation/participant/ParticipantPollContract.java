package com.framgia.fpoll.ui.pollcreation.participant;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantPollContract {
    interface View extends BaseView {
        void nextStep();
        void previousStep();
        void showCreatePollError(String message);
        void showDialog();
        void hideDialog();
    }

    interface Presenter {
        void nextStep();
        void previousStep();
    }
}
