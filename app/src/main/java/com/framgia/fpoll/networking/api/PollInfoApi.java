package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.networking.ResponseItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tuanbg on 3/6/17.
 */
public class PollInfoApi {
    public interface PollInfoService {
        @GET("/api/v1/link/{token}")
        Call<ResponseItem<DataInfoItem>> getPollInfo(@Path("token") String token);
    }
}
