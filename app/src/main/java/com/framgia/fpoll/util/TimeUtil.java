package com.framgia.fpoll.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by framgia on 20/02/2017.
 */
public class TimeUtil {
    public static String convertTimeToString(Calendar calendar) {
        return new SimpleDateFormat(Constant.TimeFormat.DATE_OUTPUT).format(calendar.getTime());
    }

    public static String getCurentTime() {
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HHmmss", Locale.getDefault());
        return date.format(currentLocalTime);
    }
}
