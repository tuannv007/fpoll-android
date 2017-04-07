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
import com.framgia.fpoll.databinding.ActivityManagePollBinding;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.ui.history.ViewPagerAdapter;
import com.framgia.fpoll.ui.pollmanage.action.EditPollFragment;
import com.framgia.fpoll.ui.pollmanage.information.PollInformationFragment;
import com.framgia.fpoll.ui.pollmanage.result.ResultVoteFragment;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_TOKEN;

public class ManagePollActivity extends BaseActivity implements ManagePollContract.View {
    private ActivityManagePollBinding mBinding;
    private ManagePollContract.Presenter mPresenter;
    private ObservableField<DataInfoItem> mPoll = new ObservableField<>();
    private String mToken;
    private ViewPagerAdapter mAdapter;

    public static Intent getPollIntent(Context context, DataInfoItem poll) {
        Intent intent = new Intent(context, ManagePollActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, poll);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent getTokenIntent(Context context, String token) {
        Intent intent = new Intent(context, ManagePollActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TOKEN, token);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_manage_poll);
        getDataFromIntent();
        mPresenter = new ManagePollPresenter(this, ManagerRepository.getInstance(this));
        mBinding.setView(this);
        if (mToken != null) mPresenter.getAllData(mToken);
        start();
    }

    @Override
    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        if (bundle.getString(BUNDLE_TOKEN) != null) {
            mToken = bundle.getString(BUNDLE_TOKEN);
        }
        if (bundle.getParcelable(BUNDLE_POLL_ITEM) != null) {
            mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PollInformationFragment.newInstance(mToken));
        fragments.add(ResultVoteFragment.newInstance(mToken));
        fragments.add(EditPollFragment.newInstance(mToken));
        String[] titles = getResources().getStringArray(R.array.array_manage);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
    }

    @Override
    public void onSuccess(DataInfoItem data) {
        mPoll.set(data);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        hideProgressDialog();
    }

    public ObservableField<DataInfoItem> getPoll() {
        return mPoll;
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
