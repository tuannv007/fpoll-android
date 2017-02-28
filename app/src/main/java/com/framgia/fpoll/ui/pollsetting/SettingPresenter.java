package com.framgia.fpoll.ui.pollsetting;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_MIN_LIMIT;
import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_SPACE;
import static com.framgia.fpoll.util.Constant.WebUrl.POLL_URL;

/**
 * Created by framgia on 23/02/2017.
 * <></>
 */
public class SettingPresenter implements SettingPollContract.Presenter {
    private SettingPollContract.View mView;
    private ObservableBoolean mShowPassword = new ObservableBoolean();
    private ObservableInt mNumberLimit = new ObservableInt();
    private ObservableField<String> mLinkPoll = new ObservableField<>();
    private RequireVoteType mRequireVoteType = RequireVoteType.NAME;

    public SettingPresenter(SettingPollContract.View view) {
        mView = view;
        mShowPassword.set(false);
        mNumberLimit.set(NUMBER_MIN_LIMIT);
        mLinkPoll.set(ActivityUtil.subLinkPoll(POLL_URL));
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

    @Override
    public void onShowPassword() {
        mShowPassword.set(!mShowPassword.get());
    }

    @Override
    public void clickAugment() {
        mNumberLimit.set(mNumberLimit.get() + NUMBER_SPACE);
    }

    @Override
    public void clickMinus() {
        if (mNumberLimit.get() > 0) mNumberLimit.set(mNumberLimit.get() - NUMBER_SPACE);
    }

    @Override
    public void setRequireVote(RequireVoteType requireVote) {
        mRequireVoteType = requireVote;
    }

    public ObservableBoolean getShowPassword() {
        return mShowPassword;
    }

    public ObservableInt getNumberLimit() {
        return mNumberLimit;
    }

    public ObservableField<String> getLinkPoll() {
        return mLinkPoll;
    }
}
