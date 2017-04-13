package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.networking.ResponseItem;
import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tuanbg on 3/20/17.
 */
public interface UpdateInfoPollService {
    @POST("api/v1/poll/update/{id}")
    Call<ResponseItem<DataInfoItem>> updateInfo(@Path("id") int id, @Body PollInfoBody poll);

    class PollInfoBody {
        @SerializedName("name")
        private String mName;
        @SerializedName("email")
        private String mEmail;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("multiple")
        private int mMutiple;
        @SerializedName("type_edit")
        private int mEditType;
        @SerializedName("date_close")
        private String mDateClose;
        @SerializedName("description")
        private String mDescription;

        public PollInfoBody(String name, String email, String title, int mutiple, int editType,
                String dateClose, String description) {
            mName = name;
            mEmail = email;
            mTitle = title;
            mMutiple = mutiple;
            mEditType = editType;
            mDateClose = dateClose;
            mDescription = description;
        }
    }
}
