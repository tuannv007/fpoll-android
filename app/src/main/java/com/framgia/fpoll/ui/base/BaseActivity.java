package com.framgia.fpoll.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.framgia.fpoll.widget.FPollProgressDialog;

/**
 * Created by Nhahv0902 on 3/30/2017.
 * <></>
 */
public class BaseActivity extends AppCompatActivity {
    private FPollProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new FPollProgressDialog(this);
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.show();
    }
}
