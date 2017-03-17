package com.framgia.fpoll.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.databinding.ActivityMainBinding;
import com.framgia.fpoll.ui.authenication.activity.AuthenticationActivity;
import com.framgia.fpoll.ui.editprofile.EditProfileActivity;
import com.framgia.fpoll.ui.feedback.FeedbackFragment;
import com.framgia.fpoll.ui.history.HistoryFragment;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.ui.introduction.IntroduceAppFragment;
import com.framgia.fpoll.ui.pollcreation.infomation.CreatePollFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.SharePreferenceUtil;

import static com.framgia.fpoll.util.Constant.RequestCode.REQUEST_LOGIN;

public class MainActivity extends AppCompatActivity
    implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    private MainContract.Presenter mPresenter;
    private ActivityMainBinding mBinding;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mPresenter = new MainPresenter(this, LoginRepository.getInstance(getApplicationContext()),
            SharePreferenceUtil.getIntances(this));
        mBinding.setPresenter((MainPresenter) mPresenter);
    }

    @Override
    public void start() {
        Toolbar toolbar = mBinding.toolbarLayout.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.action_open,
                R.string.action_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
        addFragment(CreatePollFragment.newInstance(), R.string.title_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guide:
                showHelp();
                break;
            case R.id.action_history:
                addFragment(HistoryFragment.newInstance(ViewpagerType.HISTORY, null, ""),
                    R.string.title_history);
                break;
            case R.id.action_feedback:
                addFragment(FeedbackFragment.newInstance(), R.string.title_feedback);
                break;
            case R.id.action_login:
                startActivityForResult(AuthenticationActivity.getAuthenticationIntent(this),
                    REQUEST_LOGIN);
                break;
            case R.id.action_home:
                addFragment(CreatePollFragment.newInstance(), R.string.title_home);
                break;
            case R.id.action_introduce:
                addFragment(IntroduceAppFragment.newInstance(), R.string.title_introduce_app);
                break;
            case R.id.action_log_out:
                mPresenter.logout();
                break;
            default:
                break;
        }
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void addFragment(Fragment fragment, int title) {
        ActivityUtil
            .addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.frame_layout);
        setTitle(title);
    }

    @Override
    public void showMessage(String msg) {
        ActivityUtil.showToast(getApplicationContext(), msg);
    }

    @Override
    public void showHelp() {
        Uri helpUri = Uri.parse(Constant.WebUrl.HELP_URL);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.color_teal_500))
            .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.color_teal_800))
            .build().launchUrl(this, helpUri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            mPresenter.setInformation();
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    @Override
    public void startUiProfileEdition() {
        startActivity(EditProfileActivity.getProfileEditionIntent(this));
    }
}
