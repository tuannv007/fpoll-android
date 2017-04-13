package com.framgia.fpoll.ui.votemanager.detail;

import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.ui.base.BaseView;
import java.util.List;

/**
 * Created by Nhahv0902 on 4/4/2017.
 * <></>
 */
public interface VotingDetailContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showMessage(String msg);

        void updateUI(List<VoteDetail.Result> results);
    }

    interface Presenter {
    }
}
