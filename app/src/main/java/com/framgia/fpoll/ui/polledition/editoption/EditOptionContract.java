package com.framgia.fpoll.ui.polledition.editoption;

import com.framgia.fpoll.data.model.OptionItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionContract {
    interface View extends BaseView {
        void nextStep();
        void previousStep();
        void openGallery(OptionItem optionItem, int position);
        void deletePoll(int position);
        void augmentPoll();
        void pickImage();
        void showError();
    }

    interface Presenter {
        void nextStep();
        void previousStep();
        void pickImage(OptionItem optionItem, int position);
        void deletePoll(OptionItem optionItem, int position);
        void augmentPoll();
        void pickDate(OptionItem optionItem, int position);
    }
}
