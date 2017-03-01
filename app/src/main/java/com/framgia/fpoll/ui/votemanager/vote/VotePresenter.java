package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.model.VoteItem;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class VotePresenter implements VoteContract.Presenter {
    private VoteContract.View mView;

    public VotePresenter(VoteContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void clickChooseItemAnswer(VoteItem voteItem) {
        mView.updateChoiceItem(voteItem);
    }
}
