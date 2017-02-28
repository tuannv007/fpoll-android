package com.framgia.fpoll.ui.piechartresult;

import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.ui.base.BaseView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;

import java.util.List;

/**
 * Created by framgia on 27/02/2017.
 */
public interface PieChartContract {
    interface View extends BaseView {
    }

    interface Presenter {
        void setUpPieData(List<ResultItem> itemList);
    }
}
