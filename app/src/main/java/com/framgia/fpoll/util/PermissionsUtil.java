package com.framgia.fpoll.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.framgia.fpoll.R;

/**
 * Created by tuanbg on 2/20/17.
 */
public class PermissionsUtil {
    public static boolean isAllowPermissions(Activity activity) {
        if (ContextCompat
            .checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showConfirmDialog(activity);
            } else {
                ActivityCompat.requestPermissions(activity, new
                        String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    Constant.RequestCode.PERMISSIONS_REQUEST_READ_EXTERNAL);
            }
            return false;
        }
        return true;
    }

    private static void showConfirmDialog(final Activity activity) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity)
            .setCancelable(true)
            .setTitle(R.string.title_reques_pemissions)
            .setMessage(R.string.message_request_read_pemissions)
            .setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(activity, new
                                String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            Constant.RequestCode.PERMISSIONS_REQUEST_READ_EXTERNAL);
                    }
                })
            .setNegativeButton(android.R.string.no, null);
        alertBuilder.show();
    }
}
