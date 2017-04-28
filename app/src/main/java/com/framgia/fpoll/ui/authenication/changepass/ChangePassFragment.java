package com.framgia.fpoll.ui.authenication.changepass;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.databinding.DialogChangePasswordBinding;
import com.framgia.fpoll.widget.FPollProgressDialog;
import rx.Observable;

/**
 * Created by tuanbg on 4/24/17.
 */

public class ChangePassFragment extends DialogFragment implements ChangePassContract.View {
    private ChangePassPresenter mPresenter;
    private ObservableField<String> mCurrentPassword = new ObservableField<>();
    private ObservableField<String> mNewPassword = new ObservableField<>();
    private ObservableField<String> mNewPassConfirmation = new ObservableField<>();
    private ObservableField<String> mCurrentPwError = new ObservableField<>();
    private ObservableField<String> mNewPwError = new ObservableField<>();
    private ObservableField<String> mNewPwConfirmationError = new ObservableField<>();
    private FPollProgressDialog mDialog;

    public static ChangePassFragment newInstance() {
        Bundle args = new Bundle();
        ChangePassFragment fragment = new ChangePassFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        DialogChangePasswordBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_change_password, container,
                        false);
        mPresenter = new ChangePassPresenter(this, LoginRepository.getInstance(getContext()));
        binding.setDialogFragment(this);
        return binding.getRoot();
    }

    @Override
    public void start() {

    }

    @Override
    public void dismissDialog() {
        if (getDialog() != null && getDialog().isShowing()) dismiss();
    }

    @Override
    public void changePassword() {
        mPresenter.changePassword(mCurrentPassword.get(), mNewPassword.get(),
                mNewPassConfirmation.get());
    }

    @Override
    public void showProgressDialog() {
        if (mDialog == null) {
            mDialog = new FPollProgressDialog(getContext());
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onChangePasswordError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangePasswordSuccess() {
        Toast.makeText(getContext(), R.string.msg_change_password_success, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onCurrentPasswordEmpty() {
        mCurrentPwError.set(getContext().getString(R.string.msg_current_password_not_empty));
    }

    @Override
    public void onNewPasswordEmpty() {
        mNewPwError.set(getContext().getString(R.string.msg_new_password_not_empty));
    }

    @Override
    public void onNewPasswordConfirmationEmpty() {
        mNewPwConfirmationError.set(getContext().getString(R.string.msg_new_password_confirmation_not_empty));
    }

    @Override
    public void onNewPassNotSame() {
        Toast.makeText(getContext(), R.string.msg_password_confirmation_miss_match,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissView() {
        dismiss();
    }

    public ObservableField<String> getCurrentPassword() {
        return mCurrentPassword;
    }

    public ObservableField<String> getNewPassword() {
        return mNewPassword;
    }

    public ObservableField<String> getNewPassConfirmation() {
        return mNewPassConfirmation;
    }

    public ObservableField<String> getCurrentPwError() {
        return mCurrentPwError;
    }

    public ObservableField<String> getNewPwError() {
        return mNewPwError;
    }

    public ObservableField<String> getNewPwConfirmationError() {
        return mNewPwConfirmationError;
    }
}
