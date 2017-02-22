package com.framgia.fpoll.ui.pollcreation;

/**
 * Created by framgia on 20/02/2017.
 */
public class CreationPresenter implements CreationContract.Presenter {
    private CreationContract.View mView;

    public CreationPresenter(CreationContract.View view) {
        mView = view;
    }

    @Override
    public void nextStep() {
         // TODO: next branch
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }
}
