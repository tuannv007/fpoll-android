package com.framgia.fpoll.ui.pollcreation;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 20/02/2017.
 */
public interface CreationContract {
    interface View extends BaseView {
        void showDatePicker();
    }

    interface Presenter {
        void nextStep();
        void showDatePicker();
    }
}
