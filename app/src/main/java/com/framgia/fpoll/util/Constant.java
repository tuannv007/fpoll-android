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
    }

    public static class BundleConstant {
        public static final String BUNDLE_TYPE_HISTORY = "BUNDLE_TYPE_HISTORY";
        public static final String BUNDLE_EVENT_SWITCH_UI = "BUNDLE_EVENT_SWITCH_UI";
        public static final String BUNDLE_VIEW_PAGE_TYPE = "BUNDLE_VIEW_PAGE_TYPE";
        public static final String BUNDLE_LIST_RESULT_ITEM = "BUNDLE_LIST_RESULT_ITEM";
        public static final String BUNDLE_LIST_RESULT = "BUNDLE_LIST_RESULT";
        public static final String BUNDLE_TYPE_ITEM_VOTE = "BUNDLE_TYPE_ITEM_VOTE";
        public static final String BUNDLE_OPTION_ITEM = "BUNDLE_OPTION_ITEM";
    }

    public static class RequestCode {
        public static final int IMAGE_PICKER_SELECT = 1;
        public static final int PERMISSIONS_REQUEST_READ_EXTERNAL = 2;
        public static final int REQUEST_GOOGLE = 3;
    }

    public static class TimeFormat {
        public static final String DATE_OUTPUT = "dd-MM-yyyy";
    }

    public static class Tag {
        public static final String DATE_PICKER_TAG = "Date picker dialog";
    }

    public static class ConstantApi {
        /*
        * do base url cua login khac voi base url cua register len tam thoi de
        * day va comment
        * http://quiet-fjord-67201.herokuapp.com/
        * */
        public static final String BASE_URL = "https://blooming-gorge-20159.herokuapp.com/";
    }
}
