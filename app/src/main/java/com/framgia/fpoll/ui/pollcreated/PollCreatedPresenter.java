package com.framgia.fpoll.ui.pollcreated;

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
        mView = view;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void copyLinkInvite() {
        mView.copyLinkInvite();
    }

    @Override
    public void viewLinkInvite() {
        mView.startUiLinkInviteVote();
    }

    @Override
    public void resendEmail(int pollId) {
        mRepository.resentEmail(pollId, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                mView.showMessage(data.getMessage().toString());
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
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
