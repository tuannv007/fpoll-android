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
        void showMessage(String message);
        void back();
        void showDialog();
        void hideDialog();
    }

    interface Presenter {
        void nextStep(int id);
        void showDatePicker();
        void showTimePicker();
        void saveInformation(int id);
        void back();
    }
}
