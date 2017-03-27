package com.framgia.fpoll.ui.polledition;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.ActivityModifyPollBinding;
import com.framgia.fpoll.ui.history.ViewPagerAdapter;
import com.framgia.fpoll.ui.polledition.editinformation.EditInforFragment;
import com.framgia.fpoll.ui.polledition.editoption.EditOptionFragment;
import com.framgia.fpoll.ui.polledition.editsetting.EditSettingFragment;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 15/03/2017.
 */
public class ModifyPollActivity extends AppCompatActivity implements ModifyPollContract.View {
    private ActivityModifyPollBinding mBinding;
    private ModifyPollContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;
    private PollItem mPoll = new PollItem();

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
        mPresenter = new ModifyPollPresenter(this);
        mBinding.setPresenter((ModifyPollPresenter) mPresenter);
        mBinding.setActivity(this);
        mPoll = getIntent().getParcelableExtra(BUNDLE_POLL_ITEM);
        initViewPager();
    }

    @Override
    public void start() {
    }

    @Override
    public void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(EditInforFragment.newInstance(mPoll));
        fragments.add(EditOptionFragment.newInstance(mPoll));
        fragments.add(EditSettingFragment.newInstance(mPoll));
        String[] titles = getResources().getStringArray(R.array.array_vote);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
