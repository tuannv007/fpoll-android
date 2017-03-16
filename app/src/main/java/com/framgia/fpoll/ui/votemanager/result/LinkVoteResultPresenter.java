package com.framgia.fpoll.ui.votemanager.result;

import com.framgia.fpoll.data.model.poll.Option;

/**
 * Created by anhtv on 15/03/2017.
 */
public class LinkVoteResultPresenter implements LinkVoteResultContract.Presenter {
    private LinkVoteResultContract.View mView;

    public LinkVoteResultPresenter(LinkVoteResultContract.View view) {
        mView = view;
        mView.start();
    }

    @Override
    public void openVoteDetail(Option option) {
        //TODO open Vote detail
        mView.showDetail(option);
    }
}
