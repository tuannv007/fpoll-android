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
    private static final int SPLIT_TOKEN = 4;

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
        if (mView == null || mRepository == null) return;
        mView.showProgress();
        mRepository.resentEmail(pollId, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                mView.showMessage(data.getMessage().toString());
                mView.hideProgress();
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideProgress();
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

    @Override
    public String splitToken(String token) {
        String[] words = token.split("/");
        if (words.length < 4) return "";
        return words[SPLIT_TOKEN].trim();
    }
}
