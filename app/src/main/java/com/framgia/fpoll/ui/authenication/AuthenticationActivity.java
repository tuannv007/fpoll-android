package com.framgia.fpoll.ui.authenication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fpoll.R;
import com.framgia.fpoll.ui.authenication.register.RegisterFragment;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by tuanbg on 2/9/17.
 */
public class AuthenticationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_account);
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), new RegisterFragment(), R
            .id.handle_activity);
    }
}
