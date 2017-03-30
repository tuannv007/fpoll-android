package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPollContract {
    interface View extends BaseView {
        void openGallery(Option optionItem, int position);
        void deletePoll(int position);
        void augmentPoll(int position);
        void pickImage();
        void showError();
    }

    interface Presenter {
        void pickImage(Option optionItem, int position);
        void deletePoll(Option optionItem, int position);
        void augmentPoll(int position);
        void pickDate(Option optionItem, int position);
        void deleteImage(Option option);
    }
}
