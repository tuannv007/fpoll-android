package com.framgia.fpoll.ui.pollcreation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.databinding.ActivityPollCreationBinding;
import com.framgia.fpoll.ui.pollcreated.PollCreatedActivity;
import com.framgia.fpoll.ui.pollcreation.infomation.CreatePollFragment;
import com.framgia.fpoll.ui.pollcreation.option.OptionPollFragment;
import com.framgia.fpoll.ui.pollcreation.participant.ParticipantFragment;
import com.framgia.fpoll.ui.pollcreation.setting.SettingPollFragment;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.ui.pollcreation.PollCreationType.OPTION;
import static com.framgia.fpoll.ui.pollcreation.PollCreationType.PARTICIPANT;
import static com.framgia.fpoll.ui.pollcreation.PollCreationType.SETTING;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.RequestCode.REQUEST_CREATE_POLL;

public class PollCreationActivity extends AppCompatActivity implements PollCreationContract.View {
    private final ObservableBoolean mIsShowPrevious = new ObservableBoolean(false);
    private final ObservableBoolean mIsShowFinish = new ObservableBoolean(false);
    private final ObservableBoolean mIsShowNext = new ObservableBoolean(true);
    private ActivityPollCreationBinding mBinding;
    private PollCreationContract.Presenter mPresenter;
    private PollItem mPoll;
    private PollCreationType mType = PollCreationType.INFORMATION;
    private CreatePollFragment mInformationFragment;
    private OptionPollFragment mOptionFragment;
    private SettingPollFragment mSettingFragment;
    private ParticipantFragment mParticipantFragment;

    public static Intent getIntent(Context context, Poll data) {
        Intent intent = new Intent(context, PollCreationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, data);
        intent.putExtras(bundle);
        return intent;
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) {
            mPoll = new PollItem();
            return;
        }
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_poll_creation);
        getDataFromIntent();
        mPresenter = new PollCreationPresenter(this);
        mBinding.setPresenter(mPresenter);
        mBinding.setActivity(this);
        addInformation();
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CREATE_POLL && data != null) {
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public void addFragment(Fragment fragment) {
        ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.frame_layout);
    }

    private void addInformation() {
        if (mInformationFragment == null) {
            mInformationFragment = CreatePollFragment.newInstance(mPoll);
        }
        addFragment(mInformationFragment);
        mType = PollCreationType.INFORMATION;
        setTitle(R.string.title_information);
        setIsShowPrevious(false);
        setIsShowNext(true);
        setIsShowFinish(false);
    }

    private void addOption() {
        if (mOptionFragment == null) {
            mOptionFragment = OptionPollFragment.newInstance(mPoll);
        }
        addFragment(mOptionFragment);
        mType = OPTION;
        setIsShowPrevious(true);
        setIsShowNext(true);
        setIsShowFinish(false);
        setTitle(R.string.title_option);
    }

    private void addSetting() {
        if (mSettingFragment == null) {
            mSettingFragment = SettingPollFragment.newInstance(mPoll);
        }
        addFragment(mSettingFragment);
        mType = SETTING;
        setIsShowPrevious(true);
        setIsShowNext(true);
        setIsShowFinish(false);
        setTitle(R.string.title_setting);
    }

    private void addParticipant() {
        if (mParticipantFragment == null) {
            mParticipantFragment = ParticipantFragment.newInstance(mPoll);
        }
        addFragment(mParticipantFragment);
        mType = PARTICIPANT;
        setTitle(R.string.title_participant);
        setIsShowPrevious(true);
        setIsShowNext(false);
        setIsShowFinish(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void previousUI() {
        getSupportFragmentManager().popBackStack();
        switch (mType) {
            case OPTION:
                addInformation();
                break;
            case SETTING:
                addOption();
                break;
            case PARTICIPANT:
                addSetting();
                break;
            default:
                break;
        }
    }

    @Override
    public void nextUI() {
        switch (mType) {
            case INFORMATION:
                if (mInformationFragment == null) {
                    mInformationFragment = CreatePollFragment.newInstance(mPoll);
                }
                if (!mInformationFragment.checkNextUI()) return;
                addOption();
                break;
            case OPTION:
                if (mOptionFragment == null) {
                    mOptionFragment = OptionPollFragment.newInstance(mPoll);
                }
                mOptionFragment.checkNextUI(new OptionPollFragment.OnCheckOptionListenner() {
                    @Override
                    public void onSuccessful() {
                        addSetting();
                    }
                });
                break;
            case SETTING:
                if (mSettingFragment == null) {
                    mSettingFragment = SettingPollFragment.newInstance(mPoll);
                }
                if (!mSettingFragment.checkNextUI()) return;
                addParticipant();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage(getString(R.string.msg_cancel_create_poll))
                .setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PollCreationActivity.super.onBackPressed();
                            }
                        })
                .setNegativeButton(getString(android.R.string.cancel), null)
                .create()
                .show();
    }

    @Override
    public void finishCreate() {
        if (mParticipantFragment == null) {
            mParticipantFragment = ParticipantFragment.newInstance(mPoll);
        }
        mParticipantFragment.createPoll(new OnPollCreation() {
            @Override
            public void onPollCreationSuccess(HistoryPoll data) {
                startActivityForResult(
                        PollCreatedActivity.getIntent(PollCreationActivity.this, data),
                        REQUEST_CREATE_POLL);
            }
        });
    }

    public ObservableBoolean getIsShowPrevious() {
        return mIsShowPrevious;
    }

    private void setIsShowPrevious(boolean isShowPrevious) {
        mIsShowPrevious.set(isShowPrevious);
    }

    public ObservableBoolean getIsShowFinish() {
        return mIsShowFinish;
    }

    private void setIsShowFinish(boolean isShowPrevious) {
        mIsShowFinish.set(isShowPrevious);
    }

    public ObservableBoolean getIsShowNext() {
        return mIsShowNext;
    }

    private void setIsShowNext(boolean isShowPrevious) {
        mIsShowNext.set(isShowPrevious);
    }

    public interface OnPollCreation {
        void onPollCreationSuccess(HistoryPoll data);
    }
}
