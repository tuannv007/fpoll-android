package com.framgia.fpoll.ui.mainstart;

import android.content.Intent;
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

        void hideBottomNavigation();

        void showBottomNavigation();

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
