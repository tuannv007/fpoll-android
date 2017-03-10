package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public class VotePresenter implements VoteContract.Presenter {
    private VoteContract.View mView;
    private VoteInfoRepository mVoteInfoRepository;

    public VotePresenter(VoteContract.View view, VoteInfoRepository voteInfoRepository) {
        mView = view;
        mVoteInfoRepository = voteInfoRepository;
        mView.start();
    }

    @Override
    public void voteOption(OptionModel optionModel) {
        mView.updateVoteChoice(optionModel);
    }

    @Override
    public void submitVote(VoteInfoModel voteInfoModel) {
        //TODO submit vote to server
        mView.onSubmitSuccess();
    }
}
