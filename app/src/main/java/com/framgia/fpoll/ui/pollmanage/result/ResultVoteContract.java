package com.framgia.fpoll.ui.pollmanage.result;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.ui.base.BaseView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVoteContract {
    interface View extends BaseView {
        void onSuccess(ResultVoteItem resultVote);

        void onError(String message);

        void dismissDialog();

        void showDialog();

        void exportError();

        void exportSuccess(String path);

        boolean isAllowPermissions();

        void updateBarChart(BarData barChart);

        void updatePieChart(PieData pieData);
    }

    interface Presenter {
        void exportPDF();

        void exportExcel();

        void getAllData();

        int getKey();
    }
}
