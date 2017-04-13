package com.framgia.fpoll.ui.votemanager;

import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import org.jetbrains.annotations.NotNull;

/**
 * Created by anhtv on 08/03/2017.
 */
public class LinkVotePresenter implements LinkVoteContract.Presenter {
    private LinkVoteContract.View mView;
    private VoteInfoRepository mVoteInfoRepository;

    public LinkVotePresenter(LinkVoteContract.View view, VoteInfoRepository voteInfoRepository) {
        mView = view;
        mVoteInfoRepository = voteInfoRepository;
        mView.start();
    }

    @Override
    public void getVoteInfo(String token) {
        mView.setLoading();
        mVoteInfoRepository.getVoteInfo(token, new DataCallback<VoteInfo>() {
            @Override
            public void onSuccess(@NotNull VoteInfo data) {
                mView.onGetVoteInfoSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.onGetVoteInfoFailed();
            }
        });
    }
}
