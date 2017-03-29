package com.framgia.fpoll.util;

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.framgia.fpoll.util.Constant.DataConstant.DATA_SPACE;
import static com.framgia.fpoll.util.Constant.TimeFormat.DATE_OUTPUT;

/**
 * Created by framgia on 20/02/2017.
 */
public class TimeUtil {
    private static final java.lang.String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    private static final java.lang.String DATE_FORMAT = "yyyy-MM-dd hh:mm";

    public static String convertTimeToString(Calendar calendar) {
        if (calendar == null) return DATA_SPACE;
        return new SimpleDateFormat(DATE_OUTPUT, Locale.getDefault()).format(calendar.getTime());
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

    public static boolean checkDatePassed(String date) {
        if (TextUtils.isEmpty(date)) return false;
        try {
            return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(date)
                .before(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
