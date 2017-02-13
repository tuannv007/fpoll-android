package com.framgia.fpoll.ui.main;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityMainBinding;
import com.framgia.fpoll.util.Constant;

public class MainActivity extends AppCompatActivity
    implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {
    private MainPresenter mPresenter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mPresenter = new MainPresenter(this);
    }

    @Override
    public void start() {
        mBinding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_guide) showHelp();
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showHelp() {
        Uri helpUri = Uri.parse(Constant.WebUrl.HELP_URL);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.color_teal_500))
            .setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.color_teal_800))
            .build().launchUrl(this, helpUri);
    }
}
