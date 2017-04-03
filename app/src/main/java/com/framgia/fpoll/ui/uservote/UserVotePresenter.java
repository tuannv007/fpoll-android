package com.framgia.fpoll.ui.uservote;

/**
 * Created by tuanbg on 4/3/17.
 */
public class UserVotePresenter implements UserVoteContract.Presenter {
    private UserVoteContract.View mView;

    public UserVotePresenter(UserVoteContract.View view) {
        this.mView = view;
    }

}
