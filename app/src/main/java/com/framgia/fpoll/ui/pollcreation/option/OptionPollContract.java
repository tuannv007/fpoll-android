package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPollContract {
    interface View extends BaseView {
        void nextStep();
        void previousStep();
    }

    interface Presenter {
        void nextStep();
        void previousStep();
    }
}
