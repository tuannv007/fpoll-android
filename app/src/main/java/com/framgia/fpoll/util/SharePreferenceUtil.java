package com.framgia.fpoll.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.framgia.fpoll.data.model.authorization.User;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.framgia.fpoll.util.Constant.PreferenceConstant.PREF_TOKEN;
import static com.framgia.fpoll.util.Constant.PreferenceConstant.PREF_USER;

/**
 * Created by Nhahv0902 on 3/16/2017.
 * <></>
 */
public class SharePreferenceUtil {
    private static final String SHARE_PREFERENCE = "SHARE_PREFERENCE";
    private static SharePreferenceUtil sIntances;
    private SharedPreferences mPreferences;

    private SharePreferenceUtil(Context context) {
        mPreferences = context.getSharedPreferences(SHARE_PREFERENCE, MODE_PRIVATE);
    }

    public static SharePreferenceUtil getIntances(Context context) {
        if (sIntances == null) sIntances = new SharePreferenceUtil(context);
        return sIntances;
    }

    public void writeUser(User user) {
        mPreferences.edit().putString(PREF_USER, new Gson().toJson(user)).apply();
    }

    public User getUser() {
        User user = new Gson().fromJson(mPreferences.getString(PREF_USER, null), User.class);
        return user != null ? user : new User();
    }

    public void writeToken(String token) {
        mPreferences.edit().putString(PREF_TOKEN, token).apply();
    }

    public String getToken() {
        return mPreferences.getString(PREF_TOKEN, null);
    }
}
