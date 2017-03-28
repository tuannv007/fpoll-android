package com.framgia.fpoll.ui.pollcreation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.ActivityAuthenticationBinding;
import com.framgia.fpoll.ui.pollcreation.infomation.CreatePollFragment;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

public class PollCreationActivity extends AppCompatActivity implements PollCreationContract.View {
    private ActivityAuthenticationBinding mBinding;
    private PollCreationContract.Presenter mPresenter;
    private PollItem mPoll;

    public static Intent getIntent(Context context, PollItem data) {
        Intent intent = new Intent(context, PollCreationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, data);
        intent.putExtras(bundle);
        return intent;
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) {
            mPoll = new PollItem();
            return;
        }
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);
        getDataFromIntent();
        mPresenter = new PollCreationPresenter(this);
        addFragment(CreatePollFragment.newInstance(mPoll), R.string.title_home);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addFragment(Fragment fragment, int title) {
        ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.frame_layout);
        setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
