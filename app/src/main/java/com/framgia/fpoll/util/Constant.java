package com.framgia.fpoll.util;

/**
 * Created by Nhahv0902 on 2/13/2017.
 * <></>
 */
public class Constant {
    public static final int MIN_LENGTH_PASSWORD = 6;
    public static final CharSequence TITLE_TYPE_TEXT = "text";
    public static final int TYPE_LAYOUT_GRID_TWO = 2;
    public static final String TYPE_IMAGE = "image";
    public static final long TIME_OUT_SERVER = 5;
    public static final String TYPE_DIALOG_FRAGMENT = "type_dialog_fragment";

    public class TypeSetting {
        public static final int TYPE_INPUT_EMAIL = 1;
        public static final int TYPE_INPUT_NAME = 7;
        public static final int TYPE_INPUT_EMAIL_NAME = 8;
        public static final int TYPE_NOT_EQUAL_EMAIL = 10;
        public static final int HIDENT_RESULT = 2;
        public static final int TYPE_EDIT_LINK = 3;
        public static final int TYPE_COUNT_VOTE = 4;
        public static final int TYPE_CREATE_PASSWORD = 5;
        public static final int TYPE_ADD_OPTION = 9;
        public static final int TYPE_EDIT_OPTION = 11;
    }

    public static class WebUrl {
        public static final String BASE_URL = "http://poll.framgia.vn/";
        public static final String POLL_URL = BASE_URL + "link/";
        public static final String HELP_URL =
            "https://docs.google.com/viewer?url=" + BASE_URL + "tutorial";
        public static final String DATA_SCOPE =
            "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    }

    public static class DataConstant {
        public static final String DATA_PUBLIC_PROFILE = "public_profile";
        public static final String DATA_SPACE = "";
        public static final int NUMBER_SPACE = 1;
        public static final int NUMBER_MIN_LIMIT = -1;
        public static final String DATA_PLASH = "/";
        public static final String DATA_NO_TITLE = "";
        public static final String DATA_PREFIX_TOKEN = "Bearer ";

    }

    public static class BundleConstant {
        public static final String BUNDLE_TYPE_HISTORY = "BUNDLE_TYPE_HISTORY";
        public static final String BUNDLE_EVENT_SWITCH_UI = "BUNDLE_EVENT_SWITCH_UI";
        public static final String BUNDLE_VIEW_PAGE_TYPE = "BUNDLE_VIEW_PAGE_TYPE";
        public static final String BUNDLE_LIST_RESULT_ITEM = "BUNDLE_LIST_RESULT_ITEM";
        public static final String BUNDLE_LIST_RESULT = "BUNDLE_LIST_RESULT";
        public static final String BUNDLE_TYPE_ITEM_VOTE = "BUNDLE_TYPE_ITEM_VOTE";
        public static final String BUNDLE_OPTION_ITEM = "BUNDLE_OPTION_ITEM";
        public static final String BUNDLE_POLL_ITEM = "BUNDLE_POLL_ITEM";
    }

    public static class RequestCode {
        public static final int IMAGE_PICKER_SELECT = 1;
        public static final int PERMISSIONS_REQUEST_READ_EXTERNAL = 2;
        public static final int REQUEST_GOOGLE = 3;
    }

    public static class TimeFormat {
        public static final String DATE_OUTPUT = "dd-MM-yyyy HH:mm";
    }

    public static class Tag {
        public static final String DATE_PICKER_TAG = "date_picker_dialog";
        public static final String TIME_PICKER_TAG = "time_picker_dialog";
    }

    public static class ConstantApi {
        /*
        * do base url cua login khac voi base url cua register len tam thoi de
        * day va comment
        * http://quiet-fjord-67201.herokuapp.com/
        * */
        public static final String BASE_URL = "https://quiet-fjord-67201.herokuapp.com/";
        public static final String KEY_TOKEN = "token";
        public static final String KEY_POLL_INFO = "key_poll_info";
        public static final String KEY_HISTORY = "key_history";
        public static final int KEY_MULTI_CHOOSE = 1;
        public static final int KEY_SINGER_CHOOSE = 0;
        public static final String KEY_POLL_OPTION = "key_poll_option";
        public static final String KEY_POLL_SETTING = "key_poll_setting";
    }

    public static class ConstantResponseCode {
        public static final int API_RESPONSE_CODE_OK = 200;
        public static final int API_RESPONSE_UN_PROCESSABLE = 442;
    }
}
