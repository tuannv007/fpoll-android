package com.framgia.fpoll.ui.pollmanage.result;

import android.databinding.ObservableField;
import android.util.Log;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.ui.pollmanage.result.export.ExportExcelTask;
import com.framgia.fpoll.ui.pollmanage.result.export.ExportPdfTask;
import com.framgia.fpoll.util.Constant;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVotePresenter implements ResultVoteContract.Presenter {
    public static final int TEXT_SIZE = 20;
    private VoteInfoRepository mRepository;
    private ResultVoteContract.View mView;
    private List<ResultVoteItem.Result> mResultList = new ArrayList<>();
    private ObservableField<BarData> mBarChart = new ObservableField<>();
    private ObservableField<PieData> mPieData = new ObservableField<>();
    private String mToken;
    private File mFile;
    private int mKey;

    public ResultVotePresenter(ResultVoteContract.View view, VoteInfoRepository repository,
                               String token, File file) {
        mRepository = repository;
        mToken = token;
        mView = view;
        mFile = file;
        mView.start();
        getAllData();
    }

    @Override
    public void exportPDF() {
        mKey = Constant.Export.TYPE_EXPORT_PDF;
        if (mView.isAllowPermissions()) {
            new ExportPdfTask(mResultList, new ExportExcelTask.CallBackExport() {
                @Override
                public void onExportError() {
                    mView.exportError();
                }

                @Override
                public void onExportSuccess(String path) {
                    mView.exportSuccess(path);
                }
            }).execute();
        }
    }

    @Override
    public void exportExcel() {
        if (mView.isAllowPermissions()) {
            new ExportExcelTask(mFile, mResultList, new ExportExcelTask.CallBackExport() {
                @Override
                public void onExportError() {
                    mView.exportError();
                }

                @Override
                public void onExportSuccess(String path) {
                    mView.exportSuccess(path);
                }
            }).execute();
        }
    }

    @Override
    public void getAllData() {
        if (mRepository == null) return;
        mRepository.getVoteResult(mToken, new DataCallback<ResultVoteItem>() {
            @Override
            public void onSuccess(ResultVoteItem data) {
                setUpPieData(data.getResults());
                getBarData(data.getResults());
                mView.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.onError(msg);
            }
        });
    }

    public List<BarDataSet> getDataSet(List<ResultVoteItem.Result> itemList) {
        List<BarDataSet> dataSets = new ArrayList<>();
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            barEntries.add(new BarEntry(itemList.get(i).getVoters(), i));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSets.add(barDataSet);
        return dataSets;
    }

    public List<String> getXAxisValues(List<ResultVoteItem.Result> itemList) {
        ArrayList<String> xAxis = new ArrayList<>();
        for (ResultVoteItem.Result item : itemList) {
            xAxis.add(item.getName());
        }
        return xAxis;
    }

    @Override
    public void getBarData(List<ResultVoteItem.Result> itemList) {
        mBarChart.set(new BarData(getXAxisValues(itemList), getDataSet(itemList)));
    }

    public ObservableField<BarData> getBarChart() {
        return mBarChart;
    }

    @Override
    public void setUpPieData(List<ResultVoteItem.Result> itemList) {
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            entries.add(new Entry(itemList.get(i).getVoters(), i));
            labels.add(itemList.get(i).getName());
        }
        PieDataSet pieDataSet =
            new PieDataSet(entries, Constant.DataConstant.DATA_NO_TITLE);
        pieDataSet.setValueTextSize(TEXT_SIZE);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        mPieData.set(new PieData(labels, pieDataSet));
        mPieData.get().setValueTextSize(TEXT_SIZE);
    }

    public ObservableField<PieData> getPieData() {
        return mPieData;
    }

    @Override
    public int getKey() {
        return mKey;
    }
}
