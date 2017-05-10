package com.framgia.fpoll.ui.polledition.editsetting;

import android.databinding.ObservableBoolean;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.ui.pollcreation.setting.KeySetting;
import com.framgia.fpoll.ui.pollcreation.setting.RequireVoteType;

import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_SPACE;

/**
 * Created by framgia on 17/03/2017.
 */
public class EditSettingPresenter implements EditSettingContract.Presenter {
    private final int MIN_NUMBER_VOTE = 0;
    private EditSettingContract.View mView;
    private ObservableBoolean mShowPassword = new ObservableBoolean();
    private RequireVoteType mRequireVoteType = RequireVoteType.NAME;
    private PollItem mPoll;

    public EditSettingPresenter(EditSettingContract.View view, PollItem poll) {
        mView = view;
        mShowPassword.set(false);
        mPoll = poll;
        mPoll.setNumMaxVote(String.valueOf(MIN_NUMBER_VOTE));
        mView.start();
        initData();
    }

    private void initData() {
        if (mPoll == null || mPoll.getSettings() == null || mPoll.getSettings().size() == 0) return;
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
                            mPoll.setNumMaxVote(item.getValue());
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
        mPoll.setOptimizeLink(checked);
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
    public void setRequireVote(RequireVoteType requireVote) {
        mRequireVoteType = requireVote;
        mPoll.setRequiteType(requireVote.getValue());
    }

    public PollItem getPoll() {
        return mPoll;
    }

    public ObservableBoolean getShowPassword() {
        return mShowPassword;
    }
}
