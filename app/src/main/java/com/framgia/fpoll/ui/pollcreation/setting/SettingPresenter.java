package com.framgia.fpoll.ui.pollcreation.setting;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.util.Constant;

import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_MIN_LIMIT;
import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_SPACE;

/**
 * Created by framgia on 23/02/2017.
 * <></>
 */
public class SettingPresenter implements SettingPollContract.Presenter {
    private SettingPollContract.View mView;
    private ObservableBoolean mShowPassword = new ObservableBoolean();
    private ObservableInt mNumberLimit = new ObservableInt();
    private ObservableBoolean mNotAllowSameEmail = new ObservableBoolean();
    private RequireVoteType mRequireVoteType = RequireVoteType.NAME;
    private PollItem mPoll;

    public SettingPresenter(SettingPollContract.View view, PollItem pollItem) {
        mView = view;
        mShowPassword.set(false);
        mNumberLimit.set(NUMBER_MIN_LIMIT);
        mPoll = pollItem;
        mNotAllowSameEmail.set(false);
        mView.start();
        initData();
    }

    private void initData() {
        if (mPoll == null || mPoll.getSettings() == null) return;
        for (Setting item : mPoll.getSettings()) {
            KeySetting type = KeySetting.values()[item.getKey()];
            switch (type) {
                case REQUIRE_VOTE:
                    mPoll.setRequireVote(true);
                    mPoll.setRequiteType(RequireVoteType.NAME.getValue());
                    if (item.getValue() != null) {
                        try {
                            mPoll.setRequiteType(Integer.parseInt(item.getValue()));
                        } catch (NumberFormatException e) {
                            mPoll.setRequiteType(RequireVoteType.NAME.getValue());
                        }
                    }
                    break;
                case NAME:
                    // TODO: 3/24/2017 set type for name
                    break;
                case EMAIL:
                    // TODO: 3/24/2017 set type for email
                    break;
                case NAME_EMAIL:
                    // TODO: 3/24/2017 set type for email- name
                    break;
                case NOT_COINCIDENT:
                    if (item.getValue() != null) mPoll.setCoincidentEmail(true);
                    break;
                case ADD_TYPE_EMAIL:
                    break;
                case ALL_NEW_OPTION:
                    if (item.getValue() != null) mPoll.setAllowAddOption(true);
                    break;
                case EDIT_LINK:
                    mPoll.setOptimizeLink(true);
                    if (item.getValue() != null) {
                        mPoll.setOptimizeLink(true);
                        mPoll.setTextOptimizeLink(item.getValue());
                    }
                    break;
                case EDIT_OPTION:
                    if (item.getValue() != null) mPoll.setAllowEditOption(true);
                    break;
                case HIDE_RESULT:
                    if (item.getValue() != null) mPoll.setHideResult(true);
                    break;
                case NUMBER_VOTE:
                    if (item.getValue() != null) {
                        mPoll.setMaxVote(true);
                        try {
                            mNumberLimit.set(Integer.parseInt(item.getValue()));
                        } catch (NumberFormatException e) {
                            mNumberLimit.set(0);
                        }
                    }
                    break;
                case PASSWORD:
                    if (item.getValue() != null) {
                        mPoll.setPass(item.getValue());
                        mPoll.setHasPass(true);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onCheckedRequireVote(boolean checked) {
        mPoll.setRequireVote(checked);
        if (checked) mPoll.setRequiteType(mRequireVoteType.getValue());
    }

    @Override
    public void onCheckedVotingResult(boolean checked) {
        mPoll.setHideResult(checked);
    }

    @Override
    public void onCheckedLinkPoll(boolean checked) {
        // TODO: 2/27/2017 handler when chose item Link poll
    }

    @Override
    public void onCheckedVotingLimit(boolean checked) {
        mPoll.setMaxVote(checked);
        if (checked) mPoll.setNumMaxVote(mNumberLimit.get());
    }

    @Override
    public void onCheckedSetPassword(boolean checked) {
        mPoll.setHasPass(checked);
    }

    @Override
    public boolean validateSetting() {
        //If user set limit vote number option but don't set limit number
        if (mPoll.isMaxVote() && mPoll.getNumMaxVote() < Constant.LIMIT_VOTE_NUMBER_MINIUM) {
            mView.notifyError(R.string.msg_set_vote_number);
            return false;
        }
        //If user set poll password and input password box is empty
        if (mPoll.isHasPass() && mPoll.getPass().isEmpty()) {
            mView.notifyError(R.string.msg_set_poll_password);
            return false;
        } else {
            //Set password for poll item
            mPoll.setPass(mPoll.getPass());
        }
        //Set require type vote
        if (mPoll.isRequireVote()) {
            mPoll.setRequiteType(mRequireVoteType.getValue());
        }
        //Set requite not allow same email
        if (mNotAllowSameEmail.get()) {
            mPoll.setSameEmail(mNotAllowSameEmail.get());
        }
        return true;
    }

    @Override
    public void onShowPassword() {
        mShowPassword.set(!mShowPassword.get());
    }

    @Override
    public void clickAugment() {
        if (mNumberLimit.get() == NUMBER_MIN_LIMIT) mNumberLimit.set(0);
        mNumberLimit.set(mNumberLimit.get() + NUMBER_SPACE);
        mPoll.setNumMaxVote(mNumberLimit.get());
    }

    @Override
    public void clickMinus() {
        if (mNumberLimit.get() > 0) mNumberLimit.set(mNumberLimit.get() - NUMBER_SPACE);
        mPoll.setNumMaxVote(mNumberLimit.get());
    }

    @Override
    public void changeAllowEditPoll(boolean checked) {
        // TODO: 3/13/2017 checked change allow edit
        mPoll.setAllowEditOption(checked);
    }

    @Override
    public void changeAllowAddAnswer(boolean checked) {
        // TODO: 3/13/2017 checked change allow edit
        mPoll.setAllowAddOption(checked);
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

    public ObservableBoolean getSameEmailCheck() {
        return mNotAllowSameEmail;
    }

    public PollItem getPoll() {
        return mPoll;
    }
}
