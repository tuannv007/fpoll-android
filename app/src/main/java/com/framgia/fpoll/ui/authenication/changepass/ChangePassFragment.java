package com.framgia.fpoll.ui.authenication.changepass;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.DialogChangePasswordBinding;

/**
 * Created by tuanbg on 4/24/17.
 */

public class ChangePassFragment extends DialogFragment implements ChangePassContract.View {
    private ChangePassPresenter mPresenter;

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
        mPresenter = new ChangePassPresenter(this);
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
        mPresenter.changePassword();
    }
}
