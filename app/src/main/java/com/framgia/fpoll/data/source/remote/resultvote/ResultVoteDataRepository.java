package com.framgia.fpoll.data.source.remote.resultvote;

import android.content.Context;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVoteDataRepository implements ResultVoteDataSource {
    private ResultVoteDataSource mResultVoteDataSource;
    private static ResultVoteDataRepository sVoteDataRepository;

    public ResultVoteDataRepository(ResultVoteRemoteDataSource resultVoteDataSource) {
        mResultVoteDataSource = resultVoteDataSource;
    }

    public static ResultVoteDataRepository getInstance(Context context) {
        if (sVoteDataRepository == null) {
            sVoteDataRepository =
                new ResultVoteDataRepository(ResultVoteRemoteDataSource.getInstance(context));
        }
        return sVoteDataRepository;
    }

    @Override
    public void loadData(String token, final DataCallback callback) {
        if (callback == null) return;
        mResultVoteDataSource.loadData(token, new DataCallback<ResponseItem<ResultVoteItem>>() {
            @Override
            public void onSuccess(ResponseItem<ResultVoteItem> responseItem) {
                callback.onSuccess(responseItem);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
