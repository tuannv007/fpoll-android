package com.framgia.fpoll.data.ApiRestClient;

import android.content.Context;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.ApiRestClient.APIService.ResponseItem;
import com.framgia.fpoll.data.ApiRestClient.APIService.authenticationservice.AuthenticationApi;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tuanbg on 3/1/17.
 */
public class CallbackManager<T> implements Callback<T> {
    private CallBack<T> mCallback;
    private Context mContext;

    public CallbackManager(Context context, CallBack<T> callback) {
        mCallback = callback;
        mContext = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (mCallback == null) return;
        if (response.isSuccessful()) mCallback.onResponse(response.body());
        else try {
            ResponseItem error = new Gson().fromJson(response.errorBody()
                .string(), ResponseItem.class);
            String message = "";
            for (Object str : error.getMessage()) message += str + "\n";
            mCallback.onFailure(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mCallback.onFailure(mContext.getString(R.string.msg_no_internet));
    }

    public interface CallBack<T> {
        void onResponse(T data);
        void onFailure(String message);
    }
}
