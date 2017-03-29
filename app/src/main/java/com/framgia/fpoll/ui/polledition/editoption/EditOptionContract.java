package com.framgia.fpoll.ui.polledition.editoption;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditOptionContract {
    interface View extends BaseView {
        void openGallery(Option optionItem, int position);
        void deletePoll(int position);
        void augmentPoll();
        void pickImage();
        void showError();
        void back();
        void showDialog();
        void hideDialog();
        void showMessage(String message);
        void showMessage(int resId);
    }

    interface Presenter {
        void nextStep();
        void saveOption();
        void back();
        void pickImage(Option optionItem, int position);
        void deletePoll(Option optionItem, int position);
        void augmentPoll();
        void pickDate(Option optionItem, int position);
    }
}
