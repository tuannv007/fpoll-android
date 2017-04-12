package com.framgia.fpoll.ui.pollmanage.result;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultActionHandler {
    private ResultVoteContract.Presenter mListener;

    public ResultActionHandler(ResultVoteContract.Presenter listener) {
        mListener = listener;
    }

    public void exportPdf(FloatingActionsMenu menu) {
        if (mListener != null) {
            menu.collapse();
            mListener.exportPDF();
        }
    }

    public void exportExcel(FloatingActionsMenu menu) {
        if (mListener != null) {
            menu.collapse();
            mListener.exportExcel();
        }
    }
}
