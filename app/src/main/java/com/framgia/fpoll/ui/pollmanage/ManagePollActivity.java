package com.framgia.fpoll.ui.pollmanage;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityManageBinding;
import com.framgia.fpoll.ui.history.HistoryFragment;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_VIEW_PAGE_TYPE;

public class ManagePollActivity extends AppCompatActivity implements ManagePollContract.View {
    private ActivityManageBinding mBinding;
    private ManagePollContract.Presenter mPresenter;
    private ViewpagerType mViewpagerType;

    public static Intent getManageIntent(Context context, ViewpagerType type) {
        Intent intent = new Intent(context, ManagePollActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_PAGE_TYPE, type);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage);
        getDataFromIntent();
        mPresenter = new ManagePollPresenter(this, mViewpagerType);
        mPresenter.initViewPage();
    }

    @Override
    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        mViewpagerType = (ViewpagerType) bundle.getSerializable(BUNDLE_VIEW_PAGE_TYPE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void addFragment(Fragment fragment, int resTitle) {
        setTitle(resTitle);
        ActivityUtil
            .addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.frame_layout);
    }

    @Override
    public void startUiViewPageVote() {
        addFragment(HistoryFragment.newInstance(ViewpagerType.VOTE), R.string.title_vote);
    }

    @Override
    public void startUiViewPageManage() {
        addFragment(HistoryFragment.newInstance(ViewpagerType.MANAGE), R.string.title_manage_poll);
    }
}
