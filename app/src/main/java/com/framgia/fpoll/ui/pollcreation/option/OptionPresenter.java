package com.framgia.fpoll.ui.pollcreation.option;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPresenter implements OptionPollContract.Presenter {
    private OptionPollContract.View mView;

    public OptionPresenter(OptionPollContract.View view) {
        mView = view;
    }

    @Override
    public void nextStep() {
        if (mView != null) mView.nextStep();
    }

    @Override
    public void previousStep() {
        if (mView != null) mView.previousStep();
    }
}
