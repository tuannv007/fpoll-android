package com.framgia.fpoll.ui.authenication.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityAuthenticationAccountBinding;
import com.framgia.fpoll.ui.login.LoginFragment;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by tuanbg on 2/9/17.
 * <.
 */
public class AuthenticationActivity extends AppCompatActivity
    implements AuthenticationContract.View {
    private ActivityAuthenticationAccountBinding mBinding;
    private AuthenticationContract.Presenter mPresenter;

    public static Intent getAuthenticationIntent(Context context) {
        return new Intent(context, AuthenticationActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_authentication_account);
        mPresenter = new AuthenticationPresenter(this);
    }

    @Override
    public void addFragment(Fragment fragment, int stringResource) {
        ActivityUtil
            .addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.frame_layout);
        setTitle(stringResource);
    }

    @Override
    public void start() {
        addFragment(LoginFragment.getInstance(), R.string.title_login);
    }
}
