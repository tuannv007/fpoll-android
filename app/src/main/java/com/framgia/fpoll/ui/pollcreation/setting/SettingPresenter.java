package com.framgia.fpoll.ui.pollcreation.setting;

import android.databinding.ObservableBoolean;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.util.Constant;

import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_SPACE;

/**
 * Created by framgia on 23/02/2017.
 * <></>
 */
public class SettingPresenter implements SettingPollContract.Presenter {
    private final int MIN_NUMBER_VOTE = 0;
    private SettingPollContract.View mView;
    private ObservableBoolean mShowPassword = new ObservableBoolean();
    private ObservableBoolean mNotAllowSameEmail = new ObservableBoolean();
    private RequireVoteType mRequireVoteType = RequireVoteType.NAME;
    private PollItem mPoll;

    public SettingPresenter(SettingPollContract.View view, PollItem pollItem) {
        mView = view;
        mShowPassword.set(false);
        mPoll = pollItem;
        mPoll.setNumMaxVote(String.valueOf(MIN_NUMBER_VOTE));
        mNotAllowSameEmail.set(false);
        mView.start();
        initData();
    }

    private void initData() {
        if (mPoll == null || mPoll.getSettings() == null) return;
        mPoll.setRequireVote(true);
        mPoll.setRequiteType(RequireVoteType.NAME.getValue());
        for (Setting item : mPoll.getSettings()) {
            KeySetting type = KeySetting.values()[item.getKey()];
            switch (type) {
                case REQUIRE_VOTE:
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
                    mPoll.setCoincidentEmail(true);
                    break;
                case ADD_TYPE_EMAIL:
                    break;
                case ALL_NEW_OPTION:
                    mPoll.setAllowAddOption(true);
                    break;
                case EDIT_LINK:
                    mPoll.setOptimizeLink(true);
                    if (item.getValue() != null) {
                        mPoll.setOptimizeLink(true);
                        mPoll.setTextOptimizeLink(item.getValue());
                    }
                    break;
                case EDIT_OPTION:
                    mPoll.setAllowEditOption(true);
                    break;
                case HIDE_RESULT:
                    mPoll.setHideResult(true);
                    break;
                case NUMBER_VOTE:
                    if (item.getValue() != null) {
                        mPoll.setMaxVote(true);
                        try {
                            mPoll.setNumMaxVote(String.valueOf(item.getValue()));
                        } catch (NumberFormatException e) {
                            mPoll.setNumMaxVote(String.valueOf(MIN_NUMBER_VOTE));
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
    }

    @Override
    public void onCheckedSetPassword(boolean checked) {
        mPoll.setHasPass(checked);
    }

    @Override
    public boolean validateSetting() {
        //If user set limit vote number option but don't set limit number
        if (mPoll.isMaxVote()
                && Integer.parseInt(mPoll.getNumMaxVote()) < Constant.LIMIT_VOTE_NUMBER_MINIUM) {
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
        mPoll.setNumMaxVote(String.valueOf(Integer.valueOf(mPoll.getNumMaxVote()) + NUMBER_SPACE));
    }

    @Override
    public void clickMinus() {
        mPoll.setNumMaxVote(Integer.valueOf(mPoll.getNumMaxVote()) > 0 ? String.valueOf(
                Integer.valueOf(mPoll.getNumMaxVote()) - NUMBER_SPACE)
                : String.valueOf(MIN_NUMBER_VOTE));
    }

    @Override
    public void changeAllowEditPoll(boolean checked) {
        mPoll.setAllowEditOption(checked);
    }

    @Override
    public void changeAllowAddAnswer(boolean checked) {
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

    public ObservableBoolean getSameEmailCheck() {
        return mNotAllowSameEmail;
    }

    public PollItem getPoll() {
        return mPoll;
    }
}
