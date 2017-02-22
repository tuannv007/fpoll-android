package com.framgia.fpoll.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by framgia on 20/02/2017.
 */
public class TimeUtil {
    public static String convertTimeToString(Calendar calendar) {
        return new SimpleDateFormat(Constant.TimeFormat.DATE_OUTPUT).format(calendar.getTime());
    }
}
