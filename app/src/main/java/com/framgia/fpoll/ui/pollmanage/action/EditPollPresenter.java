package com.framgia.fpoll.ui.pollmanage.action;

import android.databinding.ObservableField;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public class EditPollPresenter implements EditPollContract.Presenter {
    private EditPollContract.View mView;
    private ObservableField<String> mLinkManager = new ObservableField<>();
    private ObservableField<String> mLinkVoting = new ObservableField<>();

    public EditPollPresenter(EditPollContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void submitSaveLinkManager() {
        // TODO: 3/2/2017 call api save link manager
    }

    @Override
    public void submitSaveLinkVoting() {
        // TODO: 3/2/2017 call api save link voting
    }

    @Override
    public void viewHistory() {
        // TODO: 3/2/2017 call api view history
    }

    @Override
    public void editPoll() {
        // TODO: 3/2/2017 call api edit poll
    }

    @Override
    public void closePoll() {
        // TODO: 3/2/2017 call api close poll
    }

    @Override
    public void createDuplicate() {
        // TODO: 3/2/2017 call api create duplicate
    }

    public ObservableField<String> getLinkManager() {
        return mLinkManager;
    }

    public ObservableField<String> getLinkVoting() {
        return mLinkVoting;
    }
}
