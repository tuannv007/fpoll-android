package com.framgia.fpoll.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.framgia.fpoll.data.model.ResultItem;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.DataConstant.DATA_PLASH;
import static com.framgia.fpoll.util.Constant.DataConstant.NUMBER_SPACE;

/**
 * Created by tuanbg on 2/9/17.
 */
public class ActivityUtil {
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit();
    }

    public static void showToast(Context context, int res) {
        Toast.makeText(context, res, Toast.LENGTH_LONG).show();
    }

    public static String subLinkPoll(String pollLink) {
        return pollLink.substring(pollLink.lastIndexOf(DATA_PLASH) + NUMBER_SPACE);
    }
}
