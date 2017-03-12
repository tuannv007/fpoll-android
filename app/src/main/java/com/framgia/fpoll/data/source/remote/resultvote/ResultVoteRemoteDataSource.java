package com.framgia.fpoll.data.source.remote.resultvote;

import android.content.Context;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.CallbackManager;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.ServiceGenerator;
import com.framgia.fpoll.networking.api.ResultVoteService;

/**
 * Created by tuanbg on 3/12/17.
 */
public class ResultVoteRemoteDataSource implements ResultVoteDataSource {
    private static ResultVoteRemoteDataSource sResultVoteRemoteDataSource;
    private Context mContext;

    public ResultVoteRemoteDataSource(Context context) {
        mContext = context;
    }

    public static ResultVoteRemoteDataSource getInstance(Context context) {
        if (sResultVoteRemoteDataSource == null) {
            sResultVoteRemoteDataSource = new ResultVoteRemoteDataSource(context);
        }
        return sResultVoteRemoteDataSource;
    }

    @Override
    public void loadData(String token, final DataCallback callback) {
        if (callback == null) return;
        ResultVoteService getData =
            ServiceGenerator.createService(ResultVoteService.class);
        getData.getResultVote(token).enqueue(new CallbackManager<>(mContext,
            new CallbackManager.CallBack<ResponseItem<ResultVoteItem>>() {
                @Override
                public void onResponse(ResponseItem<ResultVoteItem> data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onFailure(String message) {
                    callback.onError(message);
                }
            }));
    }
}
