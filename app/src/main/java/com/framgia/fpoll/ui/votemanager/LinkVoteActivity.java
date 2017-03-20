package com.framgia.fpoll.ui.votemanager;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.ActivityLinkVoteBinding;
import com.framgia.fpoll.ui.history.ViewPagerAdapter;
import com.framgia.fpoll.ui.votemanager.information.VoteInformationFragment;
import com.framgia.fpoll.ui.votemanager.itemmodel.ItemStatus;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.PollBarData;
import com.framgia.fpoll.ui.votemanager.itemmodel.PollPieData;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.ui.votemanager.result.LinkVoteResultFragment;
import com.framgia.fpoll.ui.votemanager.vote.VoteFragment;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.ChartUtils;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.widget.PasswordAlertDialog;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

public class LinkVoteActivity extends AppCompatActivity implements LinkVoteContract.View,
    PasswordAlertDialog.PasswordDialogCallback {
    private static final String EXTRA_TOKEN = "EXTRA_TOKEN";
    private ActivityLinkVoteBinding mBinding;
    private LinkVoteContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;
    private VoteInfoModel mVoteInfoModel;
    private String mToken;

    public static Intent getTokenIntent(Context context, String token) {
        Intent intent = new Intent(context, LinkVoteActivity.class);
        intent.putExtra(EXTRA_TOKEN, token);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_link_vote);
        mBinding.setActivity(this);
        mPresenter = new LinkVotePresenter(this, VoteInfoRepository.getInstance(this));
        if (getIntent() == null) return;
        mToken = getIntent().getStringExtra(EXTRA_TOKEN);
        if (TextUtils.isEmpty(mToken)) return;
        mPresenter.getVoteInfo(mToken);
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
        setChartData();
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
        fragments.add(LinkVoteResultFragment.newInstance(mVoteInfoModel));
        String[] titles = getResources().getStringArray(R.array.array_vote);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
    }

    private void setChartData() {
        List<String> labels = ChartUtils.createLabels(mVoteInfoModel);
        PieDataSet pieDataSet = ChartUtils.createPieData(this, mVoteInfoModel);
        BarDataSet barDataSet = ChartUtils.createBarData(this, mVoteInfoModel);
        mVoteInfoModel.setPieData(new PollPieData(labels, pieDataSet));
        mVoteInfoModel.setBarData(new PollBarData(labels, barDataSet));
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
                    mVoteInfoModel
                        .setNumberVoteLimit(Integer.parseInt(settingList.get(i).getValue()));
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
        List<OptionModel> list = new ArrayList<>();
        for (int i = 0; i < voteInfo.getPoll().getOptions().size(); i++) {
            list.add(new OptionModel(voteInfo.getPoll().getOptions().get(i), false));
        }
        mVoteInfoModel.setOptionModels(list);
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
