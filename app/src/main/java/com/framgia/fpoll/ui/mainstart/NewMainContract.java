package com.framgia.fpoll.ui.mainstart;

import android.view.View;
import com.framgia.fpoll.ui.base.BasePresenter;
import com.framgia.fpoll.ui.base.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface NewMainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onBottomBarClick(View view);

        void onStartPollCreate();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void onStartUiPollCreation();
    }
}
