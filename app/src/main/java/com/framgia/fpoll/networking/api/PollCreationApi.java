package com.framgia.fpoll.networking.api;

import com.android.annotations.NonNull;
import com.framgia.fpoll.data.model.OptionItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.Constant;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by framgia on 06/03/2017.
 */
public class PollCreationApi {
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String MULTIPLE = "multiple";
    private static final String DATE_CLOSE = "date_close";
    private static final String LOCATION = "location";
    private static final String OPTION_TEXT = "optionText";
    private static final String OPTION_IMAGE = "optionImage";
    private static final String IS_REQUIRE_VOTE = "setting[0]";
    private static final String REQUIRE_TYPE = "setting_child[0]";
    private static final String IS_SAME_EMAIL = "setting[10]";
    private static final String IS_MAX_VOTE = "setting[4]";
    private static final String NUM_MAX_VOTE = "value[4]";
    private static final String IS_HAS_PASS = "setting[5]";
    private static final String PASS = "value[5]";
    private static final String IS_HIDE_RESULT = "setting[2]";
    private static final String MEMBER = "member";
    private static final String OPEN_SQUARE_BR = "[";
    private static final String CLOSE_SQUARE_BR = "]";

    public interface PollService {
        @POST("api/v1/poll")
        Call<ResponseItem<PollItem>> createPoll(@Body RequestBody body);
    }

    public static RequestBody getRequestBody(@NonNull PollItem pollItem) {
        List<OptionItem> optionItemList = pollItem.getOptionItemList();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart(NAME, pollItem.getName());
        builder.addFormDataPart(EMAIL, pollItem.getEmail());
        builder.addFormDataPart(TITLE, pollItem.getTitle());
        builder.addFormDataPart(DESCRIPTION, pollItem.getDescription());
        builder.addFormDataPart(MULTIPLE, String.valueOf(pollItem.isMultiple()));
        builder.addFormDataPart(DATE_CLOSE, pollItem.getDateClose());
        builder.addFormDataPart(LOCATION, pollItem.getLocation());
        builder.addFormDataPart(IS_REQUIRE_VOTE, String.valueOf(pollItem.isRequireVote()));
        builder.addFormDataPart(REQUIRE_TYPE, String.valueOf(pollItem.getRequiteType()));
        builder.addFormDataPart(IS_SAME_EMAIL, String.valueOf(pollItem.isSameEmail()));
        builder.addFormDataPart(IS_MAX_VOTE, String.valueOf(pollItem.isMaxVote()));
        builder.addFormDataPart(NUM_MAX_VOTE, String.valueOf(pollItem.getNumMaxVote()));
        builder.addFormDataPart(IS_HAS_PASS, String.valueOf(pollItem.isHasPass()));
        builder.addFormDataPart(PASS, pollItem.getPass());
        builder.addFormDataPart(IS_HIDE_RESULT, String.valueOf(pollItem.isHideResult()));
        builder.addFormDataPart(MEMBER, pollItem.getMembers());
        if (optionItemList == null) return builder.build();
        for (int i = 0; i < optionItemList.size(); i++) {
            StringBuilder fieldOptionText = new StringBuilder(OPTION_TEXT);
            fieldOptionText.append(OPEN_SQUARE_BR)
                .append(String.valueOf(i))
                .append(CLOSE_SQUARE_BR);
            builder.addFormDataPart(fieldOptionText.toString(), optionItemList.get(i).getTitle());
            File file = new File(optionItemList.get(i).getPathImage());
            if (!file.exists()) continue;
            RequestBody requestBody =
                RequestBody.create(MediaType.parse(Constant.TYPE_IMAGE), file);
            StringBuilder fieldOptionImage = new StringBuilder(OPTION_IMAGE);
            fieldOptionImage.append(OPEN_SQUARE_BR)
                .append(String.valueOf(i))
                .append(CLOSE_SQUARE_BR);
            builder.addFormDataPart(fieldOptionImage.toString(), file.getName(), requestBody);
        }
        return builder.build();
    }
}
