package com.framgia.fpoll.widget;

import android.app.ProgressDialog;
import android.content.Context;

import com.framgia.fpoll.R;

/**
 * Created by tuanbg on 3/1/17.
 */
public class FPollProgressDialog extends ProgressDialog {
    public FPollProgressDialog(Context context) {
        super(context);
        setCancelable(false);
        setMessage(context.getResources().getString(R.string.msg_loading));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
