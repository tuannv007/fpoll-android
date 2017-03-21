package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPollContract {
    interface View extends BaseView {
        void nextStep();
        void previousStep();
        void openGallery(Option optionItem, int position);
        void deletePoll(int position);
        void augmentPoll();
        void pickImage();
        void showError();
    }

    interface Presenter {
        void nextStep();
        void previousStep();
        void pickImage(Option optionItem, int position);
        void deletePoll(Option optionItem, int position);
        void augmentPoll();
        void pickDate(Option optionItem, int position);
    }
}
