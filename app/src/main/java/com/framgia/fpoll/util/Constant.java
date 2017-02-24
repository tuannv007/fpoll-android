package com.framgia.fpoll.util;

/**
 * Created by Nhahv0902 on 2/13/2017.
 * <></>
 */
public class Constant {
    public static final int MIN_LENGTH_PASSWORD = 6;
    public static final CharSequence TITLE_TYPE_TEXT = "text";
    public static final int TYPE_LAYOUT_GRID_TWO = 2;

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
    }

    public static class BundleConstant {
        public static final String BUNDLE_TYPE_HISTORY = "BUNDLE_TYPE_HISTORY";
        public static final String BUNDLE_EVENT_SWITCH_UI = "BUNDLE_EVENT_SWITCH_UI";
        public static final String BUNDLE_VIEW_PAGE_TYPE = "BUNDLE_VIEW_PAGE_TYPE";
        public static final String BUNDLE_LIST_RESULT_ITEM = "BUNDLE_LIST_RESULT_ITEM";
    }

    public static class Gender {
        public static final int MALE = 1;
        public static final int FEMALE = 0;
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
}
