package com.framgia.fpoll.ui.votemanager.detail;

import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;

/**
 * Created by Nhahv0902 on 4/4/2017.
 * <></>
 */
public class VotingDetailPresenter implements VotingDetailContract.Presenter {
    private final VotingDetailContract.View mView;
    private String mToken;
    private VoteInfoRepository mRepository;

    public VotingDetailPresenter(VotingDetailContract.View view, String token,
            VoteInfoRepository repository) {
        mView = view;
        mToken = token;
        mRepository = repository;
        mView.start();
        loadData();
    }

    private void loadData() {
        if (mView == null || mToken == null || mRepository == null) return;
        mView.showProgress();
        mRepository.getVoteDetail(mToken, new DataCallback<VoteDetail>() {
            @Override
            public void onSuccess(VoteDetail data) {
                mView.updateUI(data.getResults());
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }
}
