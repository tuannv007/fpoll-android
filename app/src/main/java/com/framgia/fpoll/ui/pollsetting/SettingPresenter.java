package com.framgia.fpoll.ui.pollsetting;

/**
 * Created by framgia on 23/02/2017.
 * <></>
 */
public class SettingPresenter implements SettingPollContract.Presenter {
    private SettingPollContract.View mView;

    public SettingPresenter(SettingPollContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void onCheckedRequireVote(boolean checked) {
        // TODO: 2/27/2017 handler when chose item radio group
    }

    @Override
    public void onCheckedVotingResult(boolean checked) {
        // TODO: 2/27/2017 handler when chose item voting result
    }

    @Override
    public void onCheckedLinkPoll(boolean checked) {
        // TODO: 2/27/2017 handler when chose item Link poll
    }

    @Override
    public void onCheckedVotingLimit(boolean checked) {
        // TODO: 2/27/2017 handler when chose voting limit
    }

    @Override
    public void onCheckedSetPassword(boolean checked) {
        // TODO: 2/27/2017 handler when chose item set password
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
