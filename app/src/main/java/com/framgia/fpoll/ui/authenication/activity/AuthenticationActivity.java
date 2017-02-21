package com.framgia.fpoll.ui.authenication.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityAuthenticationAccountBinding;
import com.framgia.fpoll.ui.authenication.login.LoginFragment;
import com.framgia.fpoll.ui.authenication.register.RegisterFragment;
import com.framgia.fpoll.ui.authenication.resetpassword.ForgotPasswordFragment;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by tuanbg on 2/9/17.
 * <.
 */
public class AuthenticationActivity extends AppCompatActivity
    implements AuthenticationContract.View {
    private ActivityAuthenticationAccountBinding mBinding;
    private AuthenticationContract.Presenter mPresenter;
    private EventSwitchUI mEventSwitchUI = new EventSwitchUI() {
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        @Override
        public void switchUiForgotPassword() {
            addFragment(ForgotPasswordFragment.newInstance(), R.string.title_forgot_password);
        }

        @Override
        public void switchUiRegister() {
            addFragment(RegisterFragment.getInstance(mEventSwitchUI), R.string.title_register);
        }

        @Override
        public void switchUiLogin() {
            addFragment(LoginFragment.getInstance(mEventSwitchUI), R.string.title_login);
        }
    };

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
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFragment(LoginFragment.getInstance(mEventSwitchUI), R.string.title_login);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public interface EventSwitchUI extends Parcelable {
        void switchUiForgotPassword();
        void switchUiRegister();
        void switchUiLogin();
    }
}
