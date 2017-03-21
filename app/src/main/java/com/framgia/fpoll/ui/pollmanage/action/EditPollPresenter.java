package com.framgia.fpoll.ui.pollmanage.action;

import android.databinding.ObservableField;

import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public class EditPollPresenter implements EditPollContract.Presenter {
    private static final String TOKEN_POLL = "NWl2uyV25WmAPmOO";
    private EditPollContract.View mView;
    private ObservableField<String> mLinkManager = new ObservableField<>();
    private ObservableField<String> mLinkVoting = new ObservableField<>();
    private String mOldLinkUser;
    private String mOldLinkAdmin;
    private ManagerRepository mRepository;
    private String mIdPoll;

    public EditPollPresenter(EditPollContract.View view, ManagerRepository repository) {
        mView = view;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void updateLinkPoll() {
        if (mRepository == null) return;
    }

    @Override
    public void viewHistory() {
        // TODO: 3/2/2017 call api view history
    }

    @Override
    public void editPoll() {
        if (mView != null) mView.startModifyPoll();
    }

    @Override
    public void closePoll() {
        if (mRepository == null) return;
        mRepository.switchPollStatus(mIdPoll, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void deleteVoting() {
        if (mRepository == null) return;
        mRepository.deleteVoting(TOKEN_POLL, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
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
