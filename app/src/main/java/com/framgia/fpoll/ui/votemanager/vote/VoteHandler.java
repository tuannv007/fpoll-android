package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.model.VoteItem;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class VoteHandler {
    private VoteContract.Presenter mListener;

    public VoteHandler(VoteContract.Presenter listener) {
        mListener = listener;
    }

    public void chooseItemAnswer(VoteItem voteItem) {
        if (mListener == null) return;
        mListener.clickChooseItemAnswer(voteItem);
    }
}
