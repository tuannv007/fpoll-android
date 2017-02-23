package com.framgia.fpoll.ui.pollsetting;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 23/02/2017.
 */
public class SettingPollContract {
    interface View extends BaseView {
        void nextStep();
        void previousStep();
    }

    interface Presenter {
        void nextStep();
        void previousStep();
    }
}
