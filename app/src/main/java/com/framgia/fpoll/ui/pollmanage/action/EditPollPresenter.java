package com.framgia.fpoll.ui.pollmanage.action;

import android.databinding.ObservableField;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public class EditPollPresenter implements EditPollContract.Presenter{
    private EditPollContract.View mView;
    private ObservableField<String> mPathLink = new ObservableField<>();

    public EditPollPresenter(EditPollContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void submitUpdateEditLink() {
        // TODO call api update edit link poll
    }

    @Override
    public void submitUpdateVoteLink() {
        // TODO call api update vote link poll
    }

    public ObservableField<String> getPathLink() {
        return mPathLink;
    }
}
