package com.framgia.fpoll.ui.pollmanage.result;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultActionHandler {
    private ResultVoteContract.Presenter mListener;

    public ResultActionHandler(ResultVoteContract.Presenter listener) {
        mListener = listener;
    }

    public void viewOption() {
        if (mListener == null) return;
        mListener.viewOption();
    }

    public void exportPdf() {
        if (mListener == null) return;
        mListener.exportPDF();
    }

    public void exportExcel() {
        if (mListener == null) return;
        mListener.exportExcel();
    }

    public void showPieChart() {
        if (mListener == null) return;
        mListener.showPieChart();
    }

    public void showBartChart(){
        if (mListener == null) return;
        mListener.showBartChart();
    }
}
