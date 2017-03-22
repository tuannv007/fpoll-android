package com.framgia.fpoll.ui.pollcreation.setting;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;

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
    private ObservableField<String> mPass = new ObservableField<>();
    private ObservableField<String> mLinkPoll = new ObservableField<>();
    private ObservableBoolean mNotAllowSameEmail = new ObservableBoolean();
    private RequireVoteType mRequireVoteType = RequireVoteType.NAME;
    private PollItem mPollItem;

    public SettingPresenter(SettingPollContract.View view, PollItem pollItem) {
        mView = view;
        mShowPassword.set(false);
        mNumberLimit.set(NUMBER_MIN_LIMIT);
        mPass.set("");
        mLinkPoll.set(ActivityUtil.subLinkPoll(POLL_URL));
        mPollItem = pollItem;
        mNotAllowSameEmail.set(false);
        mView.start();
    }

    @Override
    public void onCheckedRequireVote(boolean checked) {
        mPollItem.setRequireVote(checked);
        if (checked) mPollItem.setRequiteType(mRequireVoteType.getValue());
    }

    @Override
    public void onCheckedVotingResult(boolean checked) {
        mPollItem.setHideResult(checked);
    }

    @Override
    public void onCheckedLinkPoll(boolean checked) {
        // TODO: 2/27/2017 handler when chose item Link poll
    }

    @Override
    public void onCheckedVotingLimit(boolean checked) {
        mPollItem.setMaxVote(checked);
        if (checked) mPollItem.setNumMaxVote(mNumberLimit.get());
    }

    @Override
    public void onCheckedSetPassword(boolean checked) {
        mPollItem.setHasPass(checked);
    }

    @Override
    public void nextStep() {
        if (validateSetting() && mView != null) mView.nextStep();
    }

    private boolean validateSetting() {
        //If user set limit vote number option but don't set limit number
        if (mPollItem.isMaxVote() &&
            mPollItem.getNumMaxVote() < Constant.LIMIT_VOTE_NUMBER_MINIUM) {
            mView.notifyError(R.string.msg_set_vote_number);
            return false;
        }
        //If user set poll password and input password box is empty
        if (mPollItem.isHasPass() && mPass.get().isEmpty()) {
            mView.notifyError(R.string.msg_set_poll_password);
            return false;
        } else {
            //Set password for poll item
            mPollItem.setPass(mPass.get());
        }
        //Set require type vote
        if (mPollItem.isRequireVote()) {
            mPollItem.setRequiteType(mRequireVoteType.getValue());
        }
        //Set requite not allow same email
        if (mNotAllowSameEmail.get()) {
            mPollItem.setSameEmail(mNotAllowSameEmail.get());
        }
        return true;
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
        if (mNumberLimit.get() == NUMBER_MIN_LIMIT) mNumberLimit.set(0);
        mNumberLimit.set(mNumberLimit.get() + NUMBER_SPACE);
        mPollItem.setNumMaxVote(mNumberLimit.get());
    }

    @Override
    public void clickMinus() {
        if (mNumberLimit.get() > 0) mNumberLimit.set(mNumberLimit.get() - NUMBER_SPACE);
        mPollItem.setNumMaxVote(mNumberLimit.get());
    }

    @Override
    public void changeAllowEditPoll(boolean checked) {
        // TODO: 3/13/2017 checked change allow edit
        mPollItem.setAllowEditOption(checked);
    }

    @Override
    public void changeAllowAddAnswer(boolean checked) {
        // TODO: 3/13/2017 checked change allow edit
        mPollItem.setAllowAddOption(checked);
    }

    @Override
    public void setNotAllowSameEmail(boolean checked) {
        mNotAllowSameEmail.set(checked);
    }

    @Override
    public void setRequireVote(RequireVoteType requireVote) {
        mRequireVoteType = requireVote;
    }

    //Reset same email and type email option
    @Override
    public void resetAdditionRequire() {
        mNotAllowSameEmail.set(false);
    }

    public ObservableBoolean getShowPassword() {
        return mShowPassword;
    }

    public ObservableInt getNumberLimit() {
        return mNumberLimit;
    }

    public ObservableField<String> getPass() {
        return mPass;
    }

    public ObservableField<String> getLinkPoll() {
        return mLinkPoll;
    }

    public ObservableBoolean getSameEmailCheck() {
        return mNotAllowSameEmail;
    }
}
