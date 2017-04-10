package com.framgia.fpoll.ui.votemanager;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.ActivityLinkVoteBinding;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.ui.history.ViewPagerAdapter;
import com.framgia.fpoll.ui.pollmanage.result.ResultVoteFragment;
import com.framgia.fpoll.ui.votemanager.information.VoteInformationFragment;
import com.framgia.fpoll.ui.votemanager.itemmodel.ItemStatus;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.ui.votemanager.vote.VoteFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.widget.PasswordAlertDialog;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_TOKEN;

public class LinkVoteActivity extends BaseActivity
        implements LinkVoteContract.View, PasswordAlertDialog.PasswordDialogCallback {
    private ActivityLinkVoteBinding mBinding;
    private LinkVoteContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;
    private VoteInfoModel mVoteInfoModel;
    private String mToken;
    private DataInfoItem mPoll;

    public static Intent getPollIntent(Context context, DataInfoItem poll) {
        Intent intent = new Intent(context, LinkVoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, poll);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent getTokenIntent(Context context, String token) {
        Intent intent = new Intent(context, LinkVoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TOKEN, token);
        intent.putExtras(bundle);
        return intent;
    }

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_link_vote);
        getDataFromIntent();
        mBinding.setActivity(this);
        mPresenter = new LinkVotePresenter(this, VoteInfoRepository.getInstance(this));
        if (mToken != null) mPresenter.getVoteInfo(mToken);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setLoading() {
        mVoteInfoModel.setItemStatus(ItemStatus.LOADING);
    }

    @Override
    public void onGetVoteInfoSuccess(VoteInfo voteInfo) {
        mVoteInfoModel.setVoteInfo(voteInfo);
        mVoteInfoModel.setToken(mToken);
        setListOptionModel(voteInfo);
        setListVoteSetting(voteInfo);
        if (mVoteInfoModel.getPasswordRequired() != null) {
            PasswordAlertDialog.newInstance().show(getSupportFragmentManager(), "");
        } else {
            mVoteInfoModel.setItemStatus(ItemStatus.AVAILABLE);
        }
    }

    @Override
    public void onGetVoteInfoFailed() {
        mVoteInfoModel.setItemStatus(ItemStatus.NOT_AVAILABLE);
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.title_vote));
        mVoteInfoModel = new VoteInfoModel();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(VoteFragment.newInstance(mVoteInfoModel));
        fragments.add(VoteInformationFragment.newInstance(mVoteInfoModel));
        fragments.add(ResultVoteFragment.newInstance(mToken));
        String[] titles = getResources().getStringArray(R.array.array_vote);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
    }

    private void setListVoteSetting(VoteInfo voteInfo) {
        List<Setting> settingList = voteInfo.getPoll().getSettings();
        for (int i = 0; i < settingList.size(); i++) {
            switch (settingList.get(i).getKey()) {
                case Constant.Setting.NAME_REQUIRED:
                    mVoteInfoModel.setNameRequired(true);
                    break;
                case Constant.Setting.EMAIL_REQUIRED:
                    mVoteInfoModel.setEmailRequired(true);
                    break;
                case Constant.Setting.NAME_AND_EMAIL_REQUIRED:
                    mVoteInfoModel.setEmailAndNameRequired(true);
                    break;
                case Constant.Setting.HIDDEN_RESULT:
                    mVoteInfoModel.setHiddenResult(true);
                    break;
                case Constant.Setting.LINK_EDITABLE:
                    if (settingList.get(i).getValue() == null) break;
                    mVoteInfoModel.setLinkEdited(settingList.get(i).getValue());
                    break;
                case Constant.Setting.LIMIT_VOTE_NUMBER:
                    if (settingList.get(i).getValue() == null) break;
                    mVoteInfoModel.setNumberVoteLimit(
                            Integer.parseInt(settingList.get(i).getValue()));
                    break;
                case Constant.Setting.OPTION_EDITABLE:
                    mVoteInfoModel.setOptionEditable(true);
                    break;
                case Constant.Setting.PASSWORD_REQUIRED:
                    if (settingList.get(i).getValue() == null) break;
                    mVoteInfoModel.setPasswordRequired(settingList.get(i).getValue());
                    break;
                case Constant.Setting.EMAIL_NOT_DUPLICATE:
                    mVoteInfoModel.setSpecificEmail(true);
                    break;
                case Constant.Setting.CAN_ADD_OPTION:
                    mVoteInfoModel.setAbleToAddOption(true);
                    break;
                default:
                    break;
            }
        }
    }

    private void setListOptionModel(VoteInfo voteInfo) {
        mVoteInfoModel.setOptionModels(voteInfo.getPoll().getOptions());
    }

    @Override
    public void onClickOK(String passwordInput) {
        if (mVoteInfoModel.getPasswordRequired().equals(passwordInput)) {
            mVoteInfoModel.setItemStatus(ItemStatus.AVAILABLE);
        } else {
            ActivityUtil.showToast(this, getString(R.string.msg_confirm_pass_not_success));
            PasswordAlertDialog.newInstance().show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public void onClickCancel() {
        finish();
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }

    public VoteInfoModel getVoteInfoModel() {
        return mVoteInfoModel;
    }
}
