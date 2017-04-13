package com.framgia.fpoll.ui.history.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.framgia.fpoll.databinding.DialogPollClosedBinding;

/**
 * Created by nhahv on 4/13/2017.
 * <></>
 */

public class ClosedDialogFragment extends DialogFragment {

    public static ClosedDialogFragment newInstance() {
        return new ClosedDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        DialogPollClosedBinding binding =
                DialogPollClosedBinding.inflate(inflater, container, false);
        binding.setDialog(this);
        return binding.getRoot();
    }

    public void hideDialog() {
        if (getDialog() != null && getDialog().isShowing()) getDialog().dismiss();
    }
}
