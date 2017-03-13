package com.framgia.fpoll.ui.pollmanage.result;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.resultvote.ResultVoteDataRepository;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVotePresenter implements ResultVoteContract.Presenter {
    private ResultVoteDataRepository mRepository;
    private ResultVoteContract.View mView;

    public ResultVotePresenter(ResultVoteDataRepository repository, ResultVoteContract.View view) {
        mRepository = repository;
        mView = view;
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
        // TODO: 3/12/17 export excel
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
}
