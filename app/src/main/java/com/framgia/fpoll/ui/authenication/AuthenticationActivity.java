package com.framgia.fpoll.ui.authenication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.framgia.fpoll.R;
import com.framgia.fpoll.ui.authenication.register.RegisterFragment;
import com.framgia.fpoll.util.ActivityUtil;

import java.util.List;

/**
 * Created by tuanbg on 2/9/17.
 */
public class AuthenticationActivity extends AppCompatActivity {
    private RegisterFragment mRegisterFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_account);
        mRegisterFragment = RegisterFragment.getInstance();
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), mRegisterFragment, R
            .id.handle_activity);
    }
}
