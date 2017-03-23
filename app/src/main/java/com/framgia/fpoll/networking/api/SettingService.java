package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.networking.ResponseItem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by tuanbg on 3/22/17.
 */
public interface SettingService {
    @POST("api/v1/language")
    @FormUrlEncoded
    Call<ResponseItem> changeLanguage(@Field("lang") String language);
}
