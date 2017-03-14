package com.framgia.fpoll.ui.poll;

import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.resentemail.ResentEmailRepository;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 2/21/17.
 * <></>
 */
public class PollCreatedPresenter implements PollCreatedContract.Presenter {
    private PollCreatedContract.View mView;
    private ResentEmailRepository mRepository;

    public PollCreatedPresenter(PollCreatedContract.View view, ResentEmailRepository repository) {
        this.mView = view;
        mRepository = repository;
    }

    @Override
    public void copyLinkInvite() {
        mView.copyLinkInvite();
    }

    @Override
    public void viewLinkInvite(String token) {
        mView.startUiLinkInviteVote();
    }

    @Override
    public void resendEmail(int pollId) {
        mRepository.resentEmail(pollId, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                mView.resentSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.resentError(msg);
            }
        });
    }

    @Override
    public void copyLinkManager() {
        mView.copyLinkManager();
    }

    @Override
    public void viewLinkManager() {
        mView.startUiPollManager();
    }
}
