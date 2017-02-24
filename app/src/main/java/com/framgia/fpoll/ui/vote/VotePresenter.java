package com.framgia.fpoll.ui.vote;

import android.util.Log;

import com.framgia.fpoll.data.model.VoteItem;

import java.util.List;

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
