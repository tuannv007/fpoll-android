package com.framgia.fpoll.ui.pollmanage;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.ActivityAuthenticationBinding;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.ui.history.HistoryFragment;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.widget.FPollProgressDialog;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_VIEW_PAGE_TYPE;

public class ManagePollActivity extends BaseActivity implements ManagePollContract.View {
    private ActivityAuthenticationBinding mBinding;
    private ManagePollContract.Presenter mPresenter;
    private ViewpagerType mViewpagerType;
    private ObservableField<DataInfoItem> mDataList = new ObservableField<>();
    private FPollProgressDialog mDialog;
    private String mToken;

    public static Intent getManageIntent(Context context, ViewpagerType type, String token) {
        Intent intent = new Intent(context, ManagePollActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_PAGE_TYPE, type);
        bundle.putString(Constant.ConstantApi.KEY_TOKEN, token);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);
        getDataFromIntent();
        mPresenter =
            new ManagePollPresenter(this, mViewpagerType, ManagerRepository.getInstance(this));
        startUiViewPageManage();
        if (mToken != null) mPresenter.getAllData(mToken);
    }

    @Override
    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        mViewpagerType = (ViewpagerType) bundle.getSerializable(BUNDLE_VIEW_PAGE_TYPE);
        mToken = bundle.getString(Constant.ConstantApi.KEY_TOKEN);
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
            .addFragment(getSupportFragmentManager(), fragment, R.id.frame_layout);
    }

    @Override
    public void startUiViewPageManage() {
        addFragment(HistoryFragment.newInstance(ViewpagerType.MANAGE, mDataList.get(), mToken),
            R.string.title_manage_poll);
    }

    @Override
    public void onSuccess(DataInfoItem data) {
        mDataList.set(data);
        startUiViewPageManage();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog() {
        if (mDialog == null) mDialog = new FPollProgressDialog(this);
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();
    }
}
