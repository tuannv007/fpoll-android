package com.framgia.fpoll.ui.polledition.editsetting;

import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.ui.pollcreation.setting.RequireVoteType;

/**
 * Created by framgia on 17/03/2017.
 */
public interface EditSettingContract {
    interface View extends BaseView {
        void showMessage(String message);

        void showMessage(int resId);
    }

    interface Presenter {
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
    }
}
