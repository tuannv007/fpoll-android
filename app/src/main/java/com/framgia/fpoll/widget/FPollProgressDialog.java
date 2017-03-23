package com.framgia.fpoll.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;

/**
 * Created by tuanbg on 3/1/17.
 */
public class FPollProgressDialog extends ProgressDialog {
    public FPollProgressDialog(Context context) {
        super(context);
        setCancelable(false);
        if (getWindow() != null)
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(new ProgressBar(getContext()));
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
