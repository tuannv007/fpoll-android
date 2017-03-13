package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.networking.api.VoteInfoAPI;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.ArrayList;
import java.util.List;

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
        String name = voteInfoModel.getVoteInfo().getPoll().getName();
        String email = voteInfoModel.getVoteInfo().getPoll().getEmail();
        int idPoll = voteInfoModel.getVoteInfo().getPoll().getId();
        List<Option> options = new ArrayList<>();
        for (int i = 0; i < voteInfoModel.getOptionModels().size(); i++) {
            if (voteInfoModel.getOptionModels().get(i).isChecked()) {
                options.add(voteInfoModel.getOptionModels().get(i).getOption());
            }
        }
        VoteInfoAPI.OptionsBody optionBody =
            new VoteInfoAPI.OptionsBody(name, email, idPoll, options);
        mVoteInfoRepository.votePoll(optionBody, new DataCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                mView.onSubmitSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.onSubmitFailed(msg);
            }
        });
    }
}
