package com.framgia.fpoll.ui.vote;

import com.framgia.fpoll.data.model.VoteItem;
import com.framgia.fpoll.ui.base.BaseView;

import java.util.List;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public interface VoteContract {
    interface View extends BaseView {
        void updateChoiceItem(VoteItem voteItem);
    }

    interface Presenter {
        void clickChooseItemAnswer(VoteItem voteItem);
    }
}
