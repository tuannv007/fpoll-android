package com.framgia.fpoll.ui.votemanager.updateoption;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by nhahv on 4/17/2017.
 * <></>
 */

public interface UpdateOptionContract {
    interface View extends BaseView {

        void updateOption(Option option);

        void showPickDate(Option option);

        void pickImageFromGallery(Option option);

        void showMessage(int message);

        void showMessage(String message);

        void onDeleteImage();

        void onDismiss();

        void showProgress();

        void hideProgress();

        void updateUIVote();
    }

    interface Presenter {

    }
}
