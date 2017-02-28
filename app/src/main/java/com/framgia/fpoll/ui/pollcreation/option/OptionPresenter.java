package com.framgia.fpoll.ui.pollcreation.option;

import com.framgia.fpoll.data.model.OptionItem;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPresenter implements OptionPollContract.Presenter {
    private OptionPollContract.View mView;

    public OptionPresenter(OptionPollContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void nextStep() {
        if (mView != null) mView.nextStep();
    }

    @Override
    public void previousStep() {
        if (mView != null) mView.previousStep();
    }

    @Override
    public void pickImage(OptionItem optionItem, int position) {
        mView.openGallery(optionItem, position);
    }

    @Override
    public void deletePoll(OptionItem optionItem, int position) {
        mView.deletePoll(position);
    }

    @Override
    public void augmentPoll() {
        mView.augmentPoll();
    }
}
