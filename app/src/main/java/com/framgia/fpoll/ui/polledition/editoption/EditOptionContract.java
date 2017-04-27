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

        void augmentPoll(int position);

        void pickImage();

        void showMessage(String message);

        void showMessage(int resId);

        void updateUI(EditOptionFragment.OnCheckOptionListener listener);

        void onPickDate(Option optionItem, int position);
    }

    interface Presenter {
        void pickImage(Option optionItem, int position);

        void deletePoll(Option optionItem, int position);

        void augmentPoll(int position);

        void pickDate(Option optionItem, int position);

        void deleteImage(Option option);

        void validateNextUI(EditOptionFragment.OnCheckOptionListener listener);

        void onDeleteDate(Option option);
    }
}
