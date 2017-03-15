package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.networking.ResponseItem;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by tuanbg on 3/15/17.
 */
public interface ResentEmailService {
    @POST("api/v1/send-mail-again")
    @Multipart
    Call<ResponseItem> resentEmail(@Part("pollId") int pollId);
}
