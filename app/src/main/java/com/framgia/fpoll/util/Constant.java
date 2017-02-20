package com.framgia.fpoll.util;

/**
 * Created by Nhahv0902 on 2/13/2017.
 * <></>
 */
public class Constant {
    public static final int MIN_LENGTH_PASSWORD = 6;

    public static class WebUrl {
        public static final String BASE_URL = "http://poll.framgia.vn/";
        public static final String POLL_URL = BASE_URL + "link/";
        public static final String HELP_URL =
            "https://docs.google.com/viewer?url=" + BASE_URL + "tutorial";
        public static final String DATA_SCOPE =
            "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    }

    public static class BundleConstant {
        public static final String BUNDLE_TYPE_HISTORY = "BUNDLE_TYPE_HISTORY";
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
}
