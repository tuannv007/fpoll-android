package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.networking.ResponseItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface PollManagerAPI {
    @DELETE("api/v1/poll/{id}")
    Call<ResponseItem> switchPollStatus(@Path("id") String id);
    @DELETE("api/v1/poll/participants/{token}")
    Call<ResponseItem> deleteVoting(@Path("token") String token);
    @GET("api/v1/getPollsOfUser")
    Call<ResponseItem<List<HistoryPoll>>> getHistory(@Header("Authorization") String token);
    @GET("api/v1/getClosedPolls")
    Call<ResponseItem<List<HistoryPoll>>> getPollClosed(@Header("Authorization") String token);
    @GET("api/v1/getParticipatedPolls")
    Call<ResponseItem<List<HistoryPoll>>> getPollParticipated(
        @Header("Authorization") String token);
    @PATCH("api/v1/updateLink")
    Call<ResponseItem<List<String>>> updateLinkPoll(@Header("Authorization") String token,
                                                    @Body UpdateLinkBody body);
    class UpdateLinkBody {
        @SerializedName("oldLinkUser")
        private String mOldLinkUser;
        @SerializedName("oldLinkUser")
        private String mOldLinkAdmin;
        @SerializedName("newLinkUser")
        private String mNewLinkUser;
        @SerializedName("newLinkAdmin")
        private String mNewLinkAdmin;

        public UpdateLinkBody(String oldLinkUser, String oldLinkAdmin, String newLinkUser,
                              String newLinkAdmin) {
            mOldLinkUser = oldLinkUser;
            mOldLinkAdmin = oldLinkAdmin;
            mNewLinkUser = newLinkUser;
            mNewLinkAdmin = newLinkAdmin;
        }
    }
}
