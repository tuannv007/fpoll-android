package com.framgia.fpoll.ui.authenication.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityAuthenticationBinding;
import com.framgia.fpoll.ui.authenication.login.LoginFragment;
import com.framgia.fpoll.ui.authenication.register.RegisterFragment;
import com.framgia.fpoll.ui.authenication.resetpassword.ForgotPasswordFragment;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.ui.mainstart.NewMainActivity;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.ui.introduction.IntroduceActivity.EXTRA_OPEN_FROM_MAIN;

/**
 * Created by tuanbg on 2/9/17.
 * <.
 */
public class AuthenticationActivity extends BaseActivity implements AuthenticationContract.View {
    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;
    private ForgotPasswordFragment mPasswordFragment;
    private boolean mIsOpenFromMain;
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
            showForgotPasswordFragment();
        }

        @Override
        public void switchUiRegister() {
            showRegisterFragment();
        }

        @Override
        public void switchUiLogin() {
            showLoginFragment();
        }
    };

    public static Intent getAuthenticationIntent(Context context, boolean isOpenFromMain) {
        Intent intent = new Intent(context, AuthenticationActivity.class);
        intent.putExtra(EXTRA_OPEN_FROM_MAIN, isOpenFromMain);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAuthenticationBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_authentication);
        setSupportActionBar(binding.layoutToolbar.toolbar);
        start();
        showLoginFragment();
    }

    @Override
    public void start() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            mIsOpenFromMain = intent.getExtras().getBoolean(EXTRA_OPEN_FROM_MAIN);
        }
        initToolbar();
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(mIsOpenFromMain);
        }
        setTitle(R.string.title_login);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_skip:
                startActivity(NewMainActivity.getInstance(this));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mIsOpenFromMain) {
            getMenuInflater().inflate(R.menu.authenication_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showLoginFragment() {
        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment.newInstance(mIsOpenFromMain, mEventSwitchUI);
        }
        ActivityUtil.addFragment(getSupportFragmentManager(), mLoginFragment, R.id.frame_layout);
    }

    private void showForgotPasswordFragment() {
        if (mPasswordFragment == null) {
            mPasswordFragment = ForgotPasswordFragment.newInstance();
        }
        ActivityUtil.addFragment(getSupportFragmentManager(), mPasswordFragment, R.id.frame_layout);
        setTitle(R.string.title_forgot_password);
    }

    private void showRegisterFragment() {
        if (mRegisterFragment == null) {
            mRegisterFragment = RegisterFragment.newInstance(mEventSwitchUI);
        }
        ActivityUtil.addFragment(getSupportFragmentManager(), mRegisterFragment, R.id.frame_layout);
        setTitle(R.string.title_register);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (fragment == null || fragment instanceof LoginFragment) {
            setResult(RESULT_OK);
            finish();
            return;
        }
        showLoginFragment();
    }

    public interface EventSwitchUI extends Parcelable {
        void switchUiForgotPassword();

        void switchUiRegister();

        void switchUiLogin();
    }
}
