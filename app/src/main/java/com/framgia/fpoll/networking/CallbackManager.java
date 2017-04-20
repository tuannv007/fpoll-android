package com.framgia.fpoll.networking;

import android.content.Context;
import com.framgia.fpoll.R;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tuanbg on 3/1/17.
 */
public class CallbackManager<T extends ResponseItem> implements Callback<T> {
    private CallBack<T> mCallback;
    private Context mContext;

    public CallbackManager(Context context, CallBack<T> callback) {
        mCallback = callback;
        mContext = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (mCallback == null) return;

        if (response == null) {
            mCallback.onFailure(mContext.getString(R.string.msg_error_not_know));
            return;
        }

        if (response.isSuccessful()) {
            T responseItem = response.body();
            if (responseItem.isError()) {
                mCallback.onFailure(getStringFromList(responseItem.getMessage()));
            } else {
                mCallback.onResponse(responseItem);
            }
            return;
        }

        try {
            ResponseItem error =
                    new Gson().fromJson(response.errorBody().string(), ResponseItem.class);
            if (error == null) return;
            String message = "";
            for (Object str : error.getMessage()) {
                message += str + "\n";
            }
            mCallback.onFailure(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringFromList(List<String> list) {
        if (list == null || list.size() == 0) {
            return mContext.getString(R.string.msg_error_not_know);
        }
        String str = "";
        for (String s : list) {
            str += s + "\n";
        }
        return str;
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
