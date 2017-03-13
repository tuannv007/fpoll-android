package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.networking.ResponseItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResultVoteService {
        @GET("/api/v1/poll/result/{token}")
        Call<ResponseItem<ResultVoteItem>> getResultVote(@Path("token") String token);
    }
