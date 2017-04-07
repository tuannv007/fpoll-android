package com.framgia.fpoll.ui.joinpoll;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.ActivityJoinPollBinding;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * Created by tuanbg on 4/3/17.
 */
public class JoinPollActivity extends BaseActivity implements JoinPollContract.View {

    public static Intent getIntent(Context context) {
        return new Intent(context, JoinPollActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityJoinPollBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_join_poll);
        binding.setPresenter(new JoinPollPresenter(this, ManagerRepository.getInstance(this)));
        binding.setView(this);
    }

    @Override
    public void start() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startUIManager(String poll) {
        startActivity(ManagePollActivity.getTokenIntent(this, poll));
    }

    @Override
    public void startUIVote(String poll) {
        startActivity(LinkVoteActivity.getTokenIntent(this, poll));
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
}
