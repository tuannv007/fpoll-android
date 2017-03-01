package com.framgia.fpoll.ui.votemanager.barchart;

import android.databinding.ObservableField;

import com.framgia.fpoll.data.model.ResultItem;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tran.trung.phong on 27/02/2017.
 */
public class BarchartPresenter implements BarcharContract.Presenter {
    private BarcharContract.View mView;
    private ObservableField<BarData> mBarChart = new ObservableField<>();
    private List<ResultItem> mItems;

    public BarchartPresenter(BarcharContract.View view, List<ResultItem> resultItems) {
        mView = view;
        mItems = resultItems;
        mView.start();
    }

    public List<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            barEntries.add(new BarEntry(Float.parseFloat(mItems.get(i).getVoteNumber()), i));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSets.add(barDataSet);
        return dataSets;
    }

    public List<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        for (ResultItem item : mItems) {
            xAxis.add(item.getOption());
        }
        return xAxis;
    }

    @Override
    public void getBarData() {
        mBarChart.set(new BarData(getXAxisValues(), getDataSet()));
    }

    public ObservableField<BarData> getBarChart() {
        return mBarChart;
    }
}
