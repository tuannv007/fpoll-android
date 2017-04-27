package com.framgia.fpoll.networking.api;

import android.text.TextUtils;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.Constant;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
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

    @GET("/api/v1/poll/result/{token}")
    Call<ResponseItem<ResultVoteItem>> getVoteResult(@Path("token") String token);

    @GET("/api/v1/poll/result-detail/{token}")
    Call<ResponseItem<VoteDetail>> getVoteDetail(@Path("token") String token);

    @POST("/api/v1/poll/comment")
    Call<ResponseItem<FpollComment>> postComment(@Body CommentBody commentBody);

    @POST("/api/v1/user/vote")
    Call<ResponseItem<ParticipantVotes>> votePoll(@Body RequestBody options);

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

    public class OptionsBody {
        private static final String NAME = "name";
        private static final String EMAIL = "email";
        private static final String ID_POLL = "idPoll";
        private static final String OPTION = "option[%s]";
        private static final String OPTION_TEXT = "optionText[0]";
        private static final String OPTION_IMAGE = "optionImage[0]";
        private String mName;
        private String mEmail;
        private int mIdPoll;
        private List<Option> mListOptions;
        private String mOptionText;
        private String mOptionImage;

        public OptionsBody(String name, String email, int idPoll, List<Option> listOptions) {
            mName = name;
            mEmail = email;
            mIdPoll = idPoll;
            mListOptions = listOptions;
        }

        public OptionsBody(String name, String email, int idPoll, List<Option> listOptions,
                String optionText, String optionImage) {
            mName = name;
            mEmail = email;
            mIdPoll = idPoll;
            mListOptions = listOptions;
            mOptionText = optionText;
            mOptionImage = optionImage;
        }

        public List<Option> getListOptions() {
            return mListOptions;
        }

        public RequestBody getRequestBody() {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(NAME, this.mName);
            builder.addFormDataPart(EMAIL, this.mEmail);
            builder.addFormDataPart(ID_POLL, String.valueOf(mIdPoll));
            for (Option option : mListOptions) {
                if (!TextUtils.isEmpty(option.getName())) {
                    String optionKey = String.format(OPTION, option.getId());
                    builder.addFormDataPart(optionKey, String.valueOf(option.getId()));
                }
            }
            if (!TextUtils.isEmpty(mOptionText)) builder.addFormDataPart(OPTION_TEXT, mOptionText);
            if (!TextUtils.isEmpty(mOptionImage)) {
                File file = new File(mOptionImage);
                RequestBody requestFile = RequestBody.create(MediaType.parse(mOptionImage), file);
                builder.addFormDataPart(OPTION_IMAGE, mOptionImage, requestFile);
            }
            return builder.build();
        }
    }

    public class UpdateOptionBody {
        private static final String TYPE_EDIT = "type_edit";
        private static final String OPTION_TEXT = "optionText[%s]";
        private static final String OPTION_IMAGE = "optionImage[%s]";
        private static final int TYPE_OPTION_EDIT = 2;
        private int mOptionId;
        private String mOptionText;
        private String mOptionImage;

        public UpdateOptionBody(int optionId, String optionText, String optionImage) {
            mOptionId = optionId;
            mOptionText = optionText;
            mOptionImage = optionImage;
        }

        public RequestBody getRequestBody() {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            builder.addFormDataPart(TYPE_EDIT, String.valueOf(TYPE_OPTION_EDIT));
            if (!TextUtils.isEmpty(mOptionText)) {
                builder.addFormDataPart(String.format(OPTION_TEXT, mOptionId), mOptionText);
            }
            if (!TextUtils.isEmpty(mOptionImage)) {
                File file = new File(mOptionImage);
                RequestBody requestBody =
                        RequestBody.create(MediaType.parse(Constant.TYPE_IMAGE), file);
                builder.addFormDataPart(String.format(OPTION_IMAGE, mOptionId), mOptionImage,
                        requestBody);
            }
            return builder.build();
        }
    }
}
