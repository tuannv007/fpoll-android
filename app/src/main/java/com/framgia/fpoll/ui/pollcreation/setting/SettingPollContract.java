package com.framgia.fpoll.ui.pollcreation.setting;

import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by framgia on 23/02/2017.
 * <></>
 */
public class SettingPollContract {
    interface View extends BaseView {
        void nextStep();
        void previousStep();
        void notifyError(int msg);
    }

    interface Presenter {
        void nextStep();
        void previousStep();
        void onCheckedRequireVote(boolean checked);
        void onCheckedVotingResult(boolean checked);
        void onCheckedLinkPoll(boolean checked);
        void onCheckedVotingLimit(boolean checked);
        void onCheckedSetPassword(boolean checked);
        void onShowPassword();
        void clickAugment();
        void clickMinus();
        void setRequireVote(RequireVoteType requireVote);
        void changeAllowEditPoll(boolean checked);
        void changeAllowAddAnswer(boolean checked);
        void setNotAllowSameEmail(boolean checked);
        void resetAdditionRequire();
    }
}
