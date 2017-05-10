package com.framgia.fpoll.networking.api;

import android.text.TextUtils;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.util.ActivityUtil;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.framgia.fpoll.networking.api.UpdatePollService.EditTypeConstant.TYPE_INFORMATION;
import static com.framgia.fpoll.networking.api.UpdatePollService.EditTypeConstant.TYPE_OPTION;
import static com.framgia.fpoll.networking.api.UpdatePollService.EditTypeConstant.TYPE_SETTING;
import static com.framgia.fpoll.util.Constant.Setting.CAN_ADD_OPTION;
import static com.framgia.fpoll.util.Constant.Setting.EMAIL_NOT_DUPLICATE;
import static com.framgia.fpoll.util.Constant.Setting.HIDDEN_RESULT;
import static com.framgia.fpoll.util.Constant.Setting.LIMIT_VOTE_NUMBER;
import static com.framgia.fpoll.util.Constant.Setting.LINK_EDITABLE;
import static com.framgia.fpoll.util.Constant.Setting.OPTION_EDITABLE;
import static com.framgia.fpoll.util.Constant.Setting.PASSWORD_REQUIRED;

/**
 * Created by tuanbg on 3/20/17.
 * <></>
 */
public interface UpdatePollService {
    public static final String TYPE_EDIT = "type_edit";
    public static final String TEXT_DELETE = "1";

    @POST("api/v1/poll/update/{id}")
    Call<ResponseItem<PollItem>> updateInfo(@Path("id") int id, @Body PollInfoBody poll);

    @POST("api/v1/poll/update/{id}")
    Call<ResponseItem<PollItem>> updateOption(@Path("id") int id, @Body RequestBody requestBody);

    public static final class EditTypeConstant {
        public static final int TYPE_INFORMATION = 1;
        public static final int TYPE_OPTION = 2;
        public static final int TYPE_SETTING = 3;
    }

    public class UpdateOptionBody {
        private final String OPTION_TEXT = "option[%s][name]";
        private final String OPTION_IMAGE = "option[%s][image]";
        private final String OPTION_DELETE_IMAGE = "option[%s][delete_image]";
        private final String OPTION_ID = "option[%s][id]";
        private List<Option> options;

        public UpdateOptionBody(List<Option> optionList) {
            options = optionList;
        }

        public RequestBody getRequestBody() {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            if (options == null || options.size() == 0) return builder.build();
            int numberNewOption = 1;
            for (Option option : options) {
                if (option.getId() > 0) {
                    String index = ActivityUtil.randomString();
                    builder.addFormDataPart(String.format(OPTION_ID, index),
                            String.valueOf(option.getId()));
                    String title = null;
                    if (!TextUtils.isEmpty(option.getName())) {
                        title = option.getName();
                    }
                    if (!TextUtils.isEmpty(option.getDate())) {
                        title += option.getDate();
                    }
                    if (!TextUtils.isEmpty(title)) {
                        builder.addFormDataPart(String.format(OPTION_TEXT, index), title);
                    }
                    if (!TextUtils.isEmpty(option.getImage())) {
                        File file = new File(option.getImage());
                        if (!file.exists()) continue;
                        RequestBody requestBody =
                                RequestBody.create(MediaType.parse(option.getImage()), file);
                        builder.addFormDataPart(String.format(OPTION_IMAGE, index), file.getName(),
                                requestBody);
                    } else {
                        builder.addFormDataPart(String.format(OPTION_DELETE_IMAGE, index),
                                TEXT_DELETE);
                    }
                } else if (option.getId() == 0) {
                    if (!TextUtils.isEmpty(option.getName())) {
                        builder.addFormDataPart(String.format(OPTION_TEXT, numberNewOption),
                                option.getName());
                    }
                    if (!TextUtils.isEmpty(option.getImage())) {
                        File file = new File(option.getImage());
                        if (!file.exists()) continue;
                        RequestBody requestBody =
                                RequestBody.create(MediaType.parse(option.getImage()), file);
                        builder.addFormDataPart(String.format(OPTION_IMAGE, numberNewOption),
                                file.getName(), requestBody);
                    }
                    numberNewOption++;
                }
            }
            builder.addFormDataPart(TYPE_EDIT, String.valueOf(TYPE_OPTION));
            return builder.build();
        }
    }

    class PollInfoBody {
        @SerializedName("name")
        private String mName;
        @SerializedName("email")
        private String mEmail;
        @SerializedName("title")
        private String mTitle;
        @SerializedName("multiple")
        private boolean mMultiple;
        @SerializedName("type_edit")
        private int mEditType = TYPE_INFORMATION;
        @SerializedName("date_close")
        private String mDateClose;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("location")
        private String mLocation;

        public PollInfoBody(String name, String email, String title, boolean multiple,
                String dateClose, String description, String location) {
            mName = name;
            mEmail = email;
            mTitle = title;
            mMultiple = multiple;
            mDateClose = dateClose;
            mDescription = description;
            mLocation = location;
        }
    }

    public class UpdatePoll {
        private static final String IS_REQUIRE_VOTE = "setting[0]";
        private static final String REQUIRE_TYPE = "setting_child[0]";
        private static final String IS_SAME_EMAIL = "setting[10]";
        private static final String IS_LINK_POLL = "setting[3]";
        private static final String LINK_POLL = "value[3]";
        private static final String IS_MAX_VOTE = "setting[4]";
        private static final String NUM_MAX_VOTE = "value[4]";
        private static final String IS_HAS_PASS = "setting[5]";
        private static final String PASS = "value[5]";
        private static final String ALLOW_ADD_OPTION = "setting[9]";
        private static final String ALLOW_EDIT_OPTION = "setting[11]";
        private static final String IS_HIDE_RESULT = "setting[2]";

        public static RequestBody getSettingRequestBody(PollItem pollItem) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            if (pollItem.isRequireVote()) {
                builder.addFormDataPart(IS_REQUIRE_VOTE, String.valueOf(0));
                builder.addFormDataPart(REQUIRE_TYPE, String.valueOf(pollItem.getRequiteType()));
            }
            if (pollItem.isSameEmail()) {
                builder.addFormDataPart(IS_SAME_EMAIL, String.valueOf(EMAIL_NOT_DUPLICATE));
            }
            if (pollItem.isMaxVote()) {
                builder.addFormDataPart(IS_MAX_VOTE, String.valueOf(LIMIT_VOTE_NUMBER));
                builder.addFormDataPart(NUM_MAX_VOTE, String.valueOf(pollItem.getNumMaxVote()));
            }
            if (pollItem.isHasPass()) {
                builder.addFormDataPart(IS_HAS_PASS, String.valueOf(PASSWORD_REQUIRED));
                builder.addFormDataPart(PASS, pollItem.getPass());
            }
            if (pollItem.isHideResult()) {
                builder.addFormDataPart(IS_HIDE_RESULT, String.valueOf(HIDDEN_RESULT));
            }
            if (pollItem.isAllowAddOption()) {
                builder.addFormDataPart(ALLOW_ADD_OPTION, String.valueOf(CAN_ADD_OPTION));
            }
            if (pollItem.isAllowEditOption()) {
                builder.addFormDataPart(ALLOW_EDIT_OPTION, String.valueOf(OPTION_EDITABLE));
            }
            if (pollItem.isOptimizeLink()) {
                builder.addFormDataPart(IS_LINK_POLL, String.valueOf(LINK_EDITABLE));
                builder.addFormDataPart(LINK_POLL, pollItem.getTextOptimizeLink());
            }
            builder.addFormDataPart(TYPE_EDIT, String.valueOf(TYPE_SETTING));
            return builder.build();
        }
    }
}
