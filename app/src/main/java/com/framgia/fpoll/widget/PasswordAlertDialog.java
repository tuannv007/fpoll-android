package com.framgia.fpoll.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.DialogEnterPasswordBinding;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;

/**
 * Created by anhtv on 13/03/2017.
 */
public class PasswordAlertDialog extends DialogFragment {
    private ObservableField<String> mPassword = new ObservableField<>();
    private LayoutInflater mInflater;
    private PasswordDialogCallback mCallback;

    public static PasswordAlertDialog newInstance() {
        return new PasswordAlertDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LinkVoteActivity) mCallback = (PasswordDialogCallback) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mInflater == null) mInflater = LayoutInflater.from(getActivity());
        DialogEnterPasswordBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.dialog_enter_password, null, false);
        binding.setDialog(this);
        setCancelable(false);
        return new AlertDialog.Builder(getActivity()).setView(binding.getRoot())
                .setTitle(getString(R.string.hint_password))
                .setPositiveButton(getString(R.string.mdtp_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCallback.onClickOK(mPassword.get());
                            }
                        })
                .setNegativeButton(getString(R.string.mdtp_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mCallback.onClickCancel();
                            }
                        })
                .create();
    }

    public String getPassword() {
        return mPassword.get();
    }

    public void setPassword(String password) {
        mPassword.set(password);
    }

    public interface PasswordDialogCallback {
        void onClickOK(String passwordInput);

        void onClickCancel();
    }
}
