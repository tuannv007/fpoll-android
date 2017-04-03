package com.framgia.fpoll.ui.uservote;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityUserVoteBinding;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;

/**
 * Created by tuanbg on 4/3/17.
 */
public class UserVoteActivity extends AppCompatActivity implements UserVoteContract.View {
    private ActivityUserVoteBinding mBinding;
    private String mToken;
    private UserVotePresenter mPresenter;

    public static Intent getUserVoteActivityIntent(Context context) {
        return new Intent(context, UserVoteActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_vote);
        start();
        mPresenter = new UserVotePresenter(this);
        mBinding.setActivity(this);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.toolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_user_vote));
    }

    @Override
    public void nextToVote() {
        mToken = mBinding.editToken.getText().toString();
        startActivity(LinkVoteActivity.getTokenIntent(this, mToken));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
