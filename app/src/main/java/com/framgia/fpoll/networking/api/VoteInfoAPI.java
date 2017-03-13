package com.framgia.fpoll.networking.api;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.networking.ResponseItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by anhtv on 08/03/2017.
 * <></>
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
        private int mIdPoll;
        @SerializedName("content")
        private String mContent;

        public CommentBody(String name, int idPoll, String content) {
            mName = name;
            mIdPoll = idPoll;
            mContent = content;
        }

        public String getName() {
            return mName;
        }

        public int getIdPoll() {
            return mIdPoll;
        }

        public String getContent() {
            return mContent;
        }
    }
    @POST("/api/v1/user/vote")
    Call<ResponseItem<Object>> votePoll(@Body RequestBody options);
    public class OptionsBody {
        private static final String NAME = "name";
        private static final String EMAIL = "email";
        private static final String ID_POLL = "idPoll";
        private static final String OPTION = "option";
        private static final String OPEN_SQUARE_BR = "[";
        private static final String CLOSE_SQUARE_BR = "]";
        private String mName;
        private String mEmail;
        private int mIdPoll;
        private List<Option> mListOptions;

        public OptionsBody(String name, String email, int idPoll,
                           List<Option> listOptions) {
            mName = name;
            mEmail = email;
            mIdPoll = idPoll;
            mListOptions = listOptions;
        }

        public RequestBody getRequestBody() {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(NAME, this.mName);
            builder.addFormDataPart(EMAIL, this.mEmail);
            builder.addFormDataPart(ID_POLL, String.valueOf(this.mIdPoll));
            for (int i = 0; i < this.mListOptions.size(); i++) {
                String optionKey = OPTION + OPEN_SQUARE_BR +
                    String.valueOf(this.mListOptions.get(i).getId()) +
                    CLOSE_SQUARE_BR;
                builder.addFormDataPart(optionKey,
                    String.valueOf(this.mListOptions.get(i).getId()));
            }
            return builder.build();
        }
    }
}
