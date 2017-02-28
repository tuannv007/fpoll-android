package com.framgia.fpoll.ui.piechartresult;

import android.databinding.ObservableField;

import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.util.Constant;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 27/02/2017.
 */
public class PieChartPresenter implements PieChartContract.Presenter {
    private PieChartContract.View mView;
    private ObservableField<PieData> mPieData = new ObservableField<>();

    public PieChartPresenter(PieChartContract.View view) {
        mView = view;
    }

    @Override
    public void setUpPieData(List<ResultItem> itemList) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            entries.add(new Entry(Integer.valueOf(itemList.get(i).getVoteNumber()), i));
            labels.add(itemList.get(i).getOption());
        }
        PieDataSet pieDataSet =
            new PieDataSet(entries, Constant.DataConstant.DATA_NO_TITLE);
        pieDataSet.setValueTextSize(PieChartFragment.TEXT_SIZE);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        mPieData.set(new PieData(labels, pieDataSet));
        mPieData.get().setValueTextSize(PieChartFragment.TEXT_SIZE);
    }

    public ObservableField<PieData> getPieData() {
        return mPieData;
    }
}
