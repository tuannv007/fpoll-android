package com.framgia.fpoll.ui.pollsetting;

/**
 * Created by framgia on 23/02/2017.
 */
public class SettingPresenter implements SettingPollContract.Presenter {
    private SettingPollContract.View mView;

    public SettingPresenter(SettingPollContract.View view) {
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
