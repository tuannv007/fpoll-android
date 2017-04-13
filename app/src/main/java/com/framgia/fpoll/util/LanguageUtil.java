package com.framgia.fpoll.util;

import android.app.Activity;
import java.util.Locale;

public class LanguageUtil {
    private static void saveLocale(String lang, Activity activity) {
        SharePreferenceUtil sharePreferenceUtil = SharePreferenceUtil.getIntances(activity);
        sharePreferenceUtil.saveLanguage(lang);
    }

    public static void changeLang(String lang, Activity activity) {
        if (lang.equalsIgnoreCase("")) return;
        Locale locale = new Locale(lang);
        saveLocale(lang, activity);
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        activity.getBaseContext()
                .getResources()
                .updateConfiguration(config,
                        activity.getBaseContext().getResources().getDisplayMetrics());
    }
}
