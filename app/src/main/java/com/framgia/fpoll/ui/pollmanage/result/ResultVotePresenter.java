package com.framgia.fpoll.ui.pollmanage.result;

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
        if (mRepository == null || mToken == null) return;
        mRepository.getVoteResult(mToken, new DataCallback<ResultVoteItem>() {
            @Override
            public void onSuccess(ResultVoteItem data) {
                mView.updatePieChart(setUpPieData(data.getResults()));
                mView.updateBarChart(getBarData(data.getResults()));
                mView.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.onError(msg);
            }
        });
    }

    private BarData getBarData(List<ResultVoteItem.Result> results) {
        int size = results.size();
        List<BarDataSet> dataSets = new ArrayList<>(size);
        List<String> xValue = new ArrayList<>(size);
        ArrayList<BarEntry> yValue = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            xValue.add(results.get(i).getName());
            yValue.add(new BarEntry(results.get(i).getVoters(), i));
        }
        BarDataSet barDataSet = new BarDataSet(yValue, "");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSets.add(barDataSet);
        return new BarData(xValue, dataSets);
    }

    private PieData setUpPieData(List<ResultVoteItem.Result> itemList) {
        List<Entry> entries = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            entries.add(new Entry(itemList.get(i).getVoters(), i));
            labels.add(itemList.get(i).getName());
        }
        PieDataSet pieDataSet = new PieDataSet(entries, Constant.DataConstant.DATA_NO_TITLE);
        pieDataSet.setValueTextSize(TEXT_SIZE);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        return new PieData(labels, pieDataSet);
    }

    @Override
    public int getKey() {
        return mKey;
    }
}
