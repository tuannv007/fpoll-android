package com.framgia.fpoll.ui.pollmanage.result;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultActionHandler {
    private ResultVoteContract.Presenter mListener;

    public ResultActionHandler(ResultVoteContract.Presenter listener) {
        mListener = listener;
    }

    public void exportPdf() {
        if (mListener != null) mListener.exportPDF();
    }

    public void exportExcel() {
        if (mListener != null) mListener.exportExcel();
    }
}
