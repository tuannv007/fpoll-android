package com.framgia.fpoll.ui.pollcreated;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.resentemail.ResentEmailRepository;
import com.framgia.fpoll.databinding.ActivityPollCreatedBinding;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.ConstantApi.BASE_URL;
import static com.framgia.fpoll.util.Constant.POSITION_LINK_ADMIN;
import static com.framgia.fpoll.util.Constant.POSITION_LINK_INVITE;
import static com.framgia.fpoll.util.Constant.TITLE_TYPE_TEXT;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedActivity extends AppCompatActivity implements PollCreatedContract.View {
    private ActivityPollCreatedBinding mBinding;
    private PollCreatedPresenter mPresenter;
    private ClipboardManager mClipboardManager;
    private ObservableField<String> mLinkAdmin = new ObservableField<>();
    private ObservableField<String> mLinkUser = new ObservableField<>();
    private PollItem mPoll = new PollItem();

    public static Intent getIntent(Context context, PollItem pollItem) {
        Intent intent = new Intent(context, PollCreatedActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_poll_created);
        getDataFromIntent();
        mPresenter = new PollCreatedPresenter(this, ResentEmailRepository.getInstance(this));
        mBinding.setActivity(this);
        mBinding.setHandler(new PollCreatedHandler(mPresenter));
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        setTitle(R.string.app_name);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) {
            mPoll = new PollItem();
        } else mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (mPoll == null || mPoll.getLink().size() == 0) return;
        mLinkAdmin.set(BASE_URL + mPoll.getLink().get(POSITION_LINK_ADMIN).getToken());
        mLinkUser.set(BASE_URL + mPoll.getLink().get(POSITION_LINK_INVITE).getToken());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void copyLinkInvite() {
        if (mLinkUser.get() != null) copy(mLinkUser.get());
    }

    public void copy(String link) {
        ClipData clipData;
        clipData = ClipData.newPlainText(TITLE_TYPE_TEXT, link);
        mClipboardManager.setPrimaryClip(clipData);
        showMessage(getString(R.string.msg_copy_success));
    }

    @Override
    public void startUiPollManager() {
        String token;
        if (mPoll == null || mPoll.getLink().size() == 0 ||
            (token = mPoll.getLink().get(POSITION_LINK_ADMIN).getToken()) == null) return;
        startActivity(ManagePollActivity.getManageIntent(this, ViewpagerType.MANAGE, token));
    }

    @Override
    public void copyLinkManager() {
        if (mLinkAdmin.get() != null) copy(mLinkAdmin.get());
    }

    @Override
    public void showMessage(String msg) {
        ActivityUtil.showToast(getApplicationContext(), msg);
    }

    @Override
    public void startUiLinkInviteVote() {
        String token;
        if (mPoll == null || mPoll.getLink().size() == 0 ||
            (token = mPoll.getLink().get(POSITION_LINK_INVITE).getToken()) == null) return;
        startActivity(LinkVoteActivity.getTokenIntent(this, token));
    }

    public PollItem getPoll() {
        return mPoll;
    }

    public ObservableField<String> getLinkAdmin() {
        return mLinkAdmin;
    }

    public ObservableField<String> getLinkUser() {
        return mLinkUser;
    }
}
