package com.framgia.fpoll.ui.pollmanage.result;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.resultvote.ResultVoteDataRepository;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.ui.pollmanage.result.export.ExportExcelTask;
import com.framgia.fpoll.ui.pollmanage.result.export.ExportPdfTask;
import com.framgia.fpoll.util.Constant;

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
    private int mKey;

    public ResultVotePresenter(ResultVoteDataRepository repository, ResultVoteContract.View view,
                               List<ResultVoteItem.Result> list, File file) {
        mRepository = repository;
        mView = view;
        mList = list;
        mFile = file;
        mView.start();
    }

    @Override
    public void exportPDF() {
        mKey = Constant.Export.TYPE_EXPORT_PDF;
        if (mView.isAllowPermissions()) {
            new ExportPdfTask(mList, new ExportExcelTask.CallBackExport() {
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
            new ExportExcelTask(mFile, mList, new ExportExcelTask.CallBackExport() {
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
    public void getAllData(@NonNull String token) {
        mRepository.loadData(token, new DataCallback<ResponseItem<ResultVoteItem>>() {
            @Override
            public void onSuccess(ResponseItem<ResultVoteItem> pollInfoList) {
                mView.onSuccess(pollInfoList);
            }

            @Override
            public void onError(String message) {
                mView.onError(message);
            }
        });
    }

    @Override
    public int getKey() {
        return mKey;
    }
}
