package com.framgia.fpoll.ui.mainstart;

/**
 * Listens to user actions from the UI ({@link NewMainActivity}), retrieves the data and updates
 * the UI as required.
 */
public final class NewMainPresenter implements NewMainContract.Presenter {
    private static final String TAG = NewMainPresenter.class.getName();

    private final NewMainContract.ViewModel mViewModel;

    public NewMainPresenter(NewMainContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onStartUiPollCreation() {

    }
}
