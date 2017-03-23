package com.framgia.fpoll.util;

import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by framgia on 20/02/2017.
 */
public class TimeUtil {
    private static final java.lang.String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static String convertTimeToString(Calendar calendar) {
        if (calendar == null) return Constant.DataConstant.DATA_SPACE;
        return new SimpleDateFormat(Constant.TimeFormat.DATE_OUTPUT).format(calendar.getTime());
    }

    public static String getCurentTime() {
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HHmmss", Locale.getDefault());
        return date.format(currentLocalTime);
    }

    public static String timeToString(String time) {
        SimpleDateFormat f = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        try {
            return String.valueOf(DateUtils.getRelativeTimeSpanString(f.parse(time).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
