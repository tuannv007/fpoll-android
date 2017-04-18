package com.framgia.fpoll.ui.polledition.editinformation;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 16/03/2017.
 */
public interface EditInforContract {
    interface View extends BaseView {
        void showDatePicker();

        void bindError();

        void showTimePicker();
    }

    interface Presenter {
        void showDatePicker();
    }
}
