package com.framgia.fpoll.ui.pollmanage.result;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.ui.base.BaseView;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVoteContract {
    interface View extends BaseView {
        void onSuccess(ResponseItem<ResultVoteItem> pollInfoList);
        void onError(String message);
    }

    interface Presenter {
        void viewOption();
        void exportPDF();
        void exportExcel();
        void getAllData(@NonNull String token);
    }
}
