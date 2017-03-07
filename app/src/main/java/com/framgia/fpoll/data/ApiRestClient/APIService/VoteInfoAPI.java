package com.framgia.fpoll.data.ApiRestClient.APIService;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.voteinfo.VoteInfo;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by anhtv on 08/03/2017.
 */
public interface VoteInfoAPI {
    @GET("/api/v1/link/{token}")
    Call<ResponseItem<VoteInfo>> showVoteInfo(@Path("token") String token);
    @POST("/api/v1/poll/comment")
    Call<ResponseItem<FpollComment>> postComment(@Body CommentBody commentBody);
    public class CommentBody {
        @SerializedName("name")
        private String mName;
        @SerializedName("idPoll")
        private String mIdPoll;
        @SerializedName("content")
        private String mContent;

        public CommentBody(String name, String idPoll, String content) {
            mName = name;
            mIdPoll = idPoll;
            mContent = content;
        }

        public String getName() {
            return mName;
        }

        public String getIdPoll() {
            return mIdPoll;
        }

        public String getContent() {
            return mContent;
        }
    }
}
