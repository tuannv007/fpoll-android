package com.framgia.fpoll.ui.pollcreation;

import com.android.annotations.NonNull;

/**
 * Created by Nhahv0902 on 3/28/2017.
 * <></>
 */
public class PollCreationPresenter implements PollCreationContract.Presenter {
    private final PollCreationContract.View mView;

    public PollCreationPresenter(@NonNull PollCreationContract.View view) {
        mView = view;
        mView.start();
    }
}
