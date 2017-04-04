package com.framgia.fpoll.ui.votemanager.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.VoteDetail;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.ActivityVotingDetailBinding;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.util.ActivityUtil;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_TOKEN;

public class VotingDetailActivity extends BaseActivity implements VotingDetailContract.View {
    private ActivityVotingDetailBinding mBinding;
    private ObservableField<VotingDetailAdapter> mAdapter = new ObservableField<>();

    public static Intent getIntent(Context context, String token) {
        Intent intent = new Intent(context, VotingDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TOKEN, token);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_voting_detail);
        mBinding.setPresenter(
                new VotingDetailPresenter(this, getToken(), VoteInfoRepository.getInstance(this)));
        mBinding.setView(this);
    }

    private String getToken() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getString(BUNDLE_TOKEN) == null) return "";
        return bundle.getString(BUNDLE_TOKEN);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAdapter.set(new VotingDetailAdapter());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        hideProgressDialog();
    }

    @Override
    public void showMessage(String msg) {
        ActivityUtil.showToast(getApplicationContext(), msg);
    }

    @Override
    public void updateUI(List<VoteDetail.Result> results) {
        mAdapter.get().update(results);
    }

    public ObservableField<VotingDetailAdapter> getAdapter() {
        return mAdapter;
    }
}
