package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.networking.ResponseItem;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Nhahv0902 on 3/6/2017.
 * <></>
 */
public interface FeedbackAPI {
    @POST("api/v1/sendFeedback")
    Call<ResponseItem<String>> feedback(@Body FeedbackBody feedbackBody);
    public class FeedbackBody {
        @SerializedName("name")
        private String mName;
        @SerializedName("email")
        private String mEmail;
        @SerializedName("feedback")
        private String mContent;

        public FeedbackBody(String name, String email, String content) {
            mName = name;
            mEmail = email;
            mContent = content;
        }
    }
}
