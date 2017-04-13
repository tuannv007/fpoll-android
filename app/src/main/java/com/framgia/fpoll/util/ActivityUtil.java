package com.framgia.fpoll.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import java.util.List;

import static com.framgia.fpoll.util.Constant.DataConstant.DATA_PLASH;
import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_SPACE;

/**
 * Created by tuanbg on 2/9/17.
 */
public class ActivityUtil {
    public static void addFragment(@NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit();
    }

    public static String byString(List<String> data) {
        StringBuilder message = new StringBuilder();
        for (String msg : data) {
            message.append(msg).append("\n");
        }
        return message.toString();
    }

    public static void showToast(Context context, int res) {
        if (context == null) return;
        Toast.makeText(context, res, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static String subLinkPoll(String pollLink) {
        return pollLink.substring(pollLink.lastIndexOf(DATA_PLASH) + NUMBER_SPACE);
    }
}
