package com.framgia.fpoll.ui.pollmanage.result;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.resultvote.ResultVoteDataRepository;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.ui.pollmanage.result.export.ExportDatabaseCSVTask;

import java.io.File;
import java.util.List;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVotePresenter implements ResultVoteContract.Presenter {
    private ResultVoteDataRepository mRepository;
    private ResultVoteContract.View mView;
    private List<ResultVoteItem.Result> mList;
    private File mFile;

    public ResultVotePresenter(ResultVoteDataRepository repository, ResultVoteContract.View view,
                               List<ResultVoteItem.Result> list, File file) {
        mRepository = repository;
        mView = view;
        mList = list;
        mFile = file;
    }

    @Override
    public void viewOption() {
        // TODO: 3/12/17 view option
    }

    @Override
    public void exportPDF() {
        // TODO: 3/12/17 export pdf
    }

    @Override
    public void exportExcel() {
        mView.startExport();
    }

    @Override
    public void export() {
        new ExportDatabaseCSVTask(mFile, mList,
            new ExportDatabaseCSVTask.CallBackExport() {
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

    @Override
    public void getAllData(@NonNull String token) {
        mView.showDialog();
        mRepository.loadData(token, new DataCallback<ResponseItem<ResultVoteItem>>() {
            @Override
            public void onSuccess(ResponseItem<ResultVoteItem> pollInfoList) {
                mView.onSuccess(pollInfoList);
                mView.dismissDialog();
            }

            @Override
            public void onError(String message) {
                mView.onError(message);
                mView.dismissDialog();
            }
        });
    }
}
