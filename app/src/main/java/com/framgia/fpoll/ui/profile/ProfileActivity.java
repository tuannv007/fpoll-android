package com.framgia.fpoll.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.databinding.ActivityProfileBinding;
import com.framgia.fpoll.util.SharePreferenceUtil;

public class ProfileActivity extends AppCompatActivity {
    private ProfileContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        return new Intent(context, ProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_profile);
        mViewModel = new ProfileViewModel(this);
        ProfileContract.Presenter presenter =
                new ProfilePresenter(mViewModel, SharePreferenceUtil.getIntances(this),
                        LoginRepository.getInstance(this));
        mViewModel.setPresenter(presenter);
        binding.setViewModel((ProfileViewModel) mViewModel);
    }

    @Override
    public void onBackPressed() {
        if (mViewModel.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
