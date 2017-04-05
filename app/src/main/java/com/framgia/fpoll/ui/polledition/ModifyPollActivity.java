package com.framgia.fpoll.ui.polledition;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.ActivityModifyPollBinding;
import com.framgia.fpoll.ui.base.BaseActivity;
import com.framgia.fpoll.ui.pollcreation.PollCreationType;
import com.framgia.fpoll.ui.polledition.editinformation.EditInforFragment;
import com.framgia.fpoll.ui.polledition.editoption.EditOptionFragment;
import com.framgia.fpoll.ui.polledition.editsetting.EditSettingFragment;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.ui.pollcreation.PollCreationType.OPTION;
import static com.framgia.fpoll.ui.pollcreation.PollCreationType.SETTING;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 15/03/2017.
 */
public class ModifyPollActivity extends BaseActivity implements ModifyPollContract.View {
    private ActivityModifyPollBinding mBinding;
    private ModifyPollContract.Presenter mPresenter;
    private PollItem mPoll;
    private PollCreationType mType = PollCreationType.INFORMATION;
    private EditInforFragment mInformationFragment;
    private EditOptionFragment mOptionFragment;
    private EditSettingFragment mSettingFragment;
    private ObservableField<String> mTitleSave = new ObservableField<>();
    private ObservableField<String> mTitleNext = new ObservableField<>();
    private ObservableField<String> mTitlePrevious = new ObservableField<>();
    private ObservableBoolean mIsShowPrevious = new ObservableBoolean(false);
    private ObservableBoolean mIsShowNext = new ObservableBoolean(true);

    public static Intent getModifyIntent(Context context, PollItem data) {
        Intent intent = new Intent(context, ModifyPollActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, data);
        intent.putExtras(bundle);
        return intent;
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) return;
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (mPoll == null) mPoll = new PollItem();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify_poll);
        getDataFromIntent();
        mPresenter = new ModifyPollPresenter(this, mPoll, PollRepository.getInstance(this));
        mBinding.setPresenter((ModifyPollPresenter) mPresenter);
        mBinding.setActivity(this);
        addInformation();
    }

    @Override
    public void start() {
        setSupportActionBar(mBinding.layoutToolbar.toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTitleSave.set(getString(R.string.action_save_information));
        mTitleNext.set(getString(R.string.action_next_option));
        mTitlePrevious.set(getString(R.string.action_back_information));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void addFragment(Fragment fragment) {
        ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.frame_layout);
    }

    private void addInformation() {
        if (mInformationFragment == null) {
            mInformationFragment = EditInforFragment.newInstance(mPoll);
        }
        addFragment(mInformationFragment);
        mType = PollCreationType.INFORMATION;
        setTitle(R.string.title_information);
        mTitleSave.set(getString(R.string.action_save_information));
        mTitleNext.set(getString(R.string.action_next_option));
        mTitlePrevious.set(getString(R.string.action_back_information));
        mIsShowPrevious.set(false);
        mIsShowNext.set(true);
    }

    private void addOption() {
        if (mOptionFragment == null) {
            mOptionFragment = EditOptionFragment.newInstance(mPoll);
        }
        addFragment(mOptionFragment);
        mType = OPTION;
        setTitle(R.string.title_option);
        mTitleSave.set(getString(R.string.action_save_option));
        mTitleNext.set(getString(R.string.action_next_setting));
        mTitlePrevious.set(getString(R.string.action_back_information));
        mIsShowPrevious.set(true);
        mIsShowNext.set(true);
    }

    private void addSetting() {
        if (mSettingFragment == null) {
            mSettingFragment = EditSettingFragment.newInstance(mPoll);
        }
        addFragment(mSettingFragment);
        mType = SETTING;
        setTitle(R.string.title_setting);
        mTitleSave.set(getString(R.string.action_save_setting));
        mTitleNext.set(getString(R.string.action_next_setting));
        mTitlePrevious.set(getString(R.string.action_back_option));
        mIsShowPrevious.set(true);
        mIsShowNext.set(false);
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
    public void previousUI() {
        switch (mType) {
            case OPTION:
                addInformation();
                break;
            case SETTING:
                addOption();
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
                    mInformationFragment = EditInforFragment.newInstance(mPoll);
                }
                if (!mInformationFragment.checkNextUI()) return;
                addOption();
                break;
            case OPTION:
                if (mOptionFragment == null) {
                    mOptionFragment = EditOptionFragment.newInstance(mPoll);
                }
                mOptionFragment.checkNextUI(new EditOptionFragment.OnCheckOptionListener() {
                    @Override
                    public void onSuccessful() {
                        addSetting();
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void submitEditPoll() {
        switch (mType) {
            case INFORMATION:
                mPresenter.submitEditInformation();
                break;
            case OPTION:
                mPresenter.submitEditOption();
                break;
            case SETTING:
                mPresenter.submitEditSetting();
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        ActivityUtil.showToast(getApplicationContext(), message);
    }

    @Override
    public void showMessage(int message) {
        ActivityUtil.showToast(getApplicationContext(), message);
    }

    public ObservableField<String> getTitleSave() {
        return mTitleSave;
    }

    public ObservableField<String> getTitleNext() {
        return mTitleNext;
    }

    public ObservableField<String> getTitlePrevious() {
        return mTitlePrevious;
    }

    public ObservableBoolean getIsShowPrevious() {
        return mIsShowPrevious;
    }

    public ObservableBoolean getIsShowNext() {
        return mIsShowNext;
    }
}
