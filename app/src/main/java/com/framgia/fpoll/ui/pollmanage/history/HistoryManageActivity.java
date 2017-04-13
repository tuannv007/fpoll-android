package com.framgia.fpoll.ui.pollmanage.history;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.ActivityHistoryManageBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.widget.FPollProgressDialog;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.ConstantApi.KEY_TOKEN;

public class HistoryManageActivity extends AppCompatActivity implements HistoryManageContract.View {
    private ActivityHistoryManageBinding mBinding;
    private HistoryManageContract.Presenter mPresenter;
    private ObservableField<User> mUser = new ObservableField<>();
    private FPollProgressDialog mDialog;
    private String mToken;
    private ObservableField<HistoryAdapter> mAdapter = new ObservableField<>();
    private List<FpollComment> mListItems = new ArrayList<>();

    public static Intent getInstance(Context context, String token) {
        Intent intent = new Intent(context, HistoryManageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOKEN, token);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_history_manage);
        mPresenter = new HistoryManagePresenter(this, PollRepository.getInstance(this));
        mBinding.setActivity(this);
        start();
        mPresenter.getData(mToken);
    }

    @Override
    public void start() {
        Toolbar toolbar = mBinding.toolbarLayout.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.title_history));
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        mToken = bundle.getString(KEY_TOKEN);
    }

    @Override
    public void onSuccess(DataInfoItem data) {
        mListItems.clear();
        mUser.set(data.getPoll().getUser());
        mBinding.setUser(mUser.get());
        mListItems.addAll(data.getFpollComment());
        mAdapter.set(new HistoryAdapter(mListItems));
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

    @Override
    public void onError(String msg) {
        ActivityUtil.showToast(getApplicationContext(), msg);
    }

    public ObservableField<HistoryAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
