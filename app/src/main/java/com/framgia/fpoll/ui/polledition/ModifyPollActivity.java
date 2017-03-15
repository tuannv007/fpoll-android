package com.framgia.fpoll.ui.polledition;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.ActivityModifyPollBinding;
import com.framgia.fpoll.ui.history.ViewPagerAdapter;

/**
 * Created by framgia on 15/03/2017.
 */
public class ModifyPollActivity extends AppCompatActivity implements ModifyPollContract.View {
    private ActivityModifyPollBinding mBinding;
    private ModifyPollContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify_poll);
        mPresenter = new ModifyPollPresenter(this);
        mBinding.setPresenter((ModifyPollPresenter) mPresenter);
        mBinding.setActivity(this);
        initViewPager();
    }

    @Override
    public void start() {
    }

    @Override
    public void initViewPager() {
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
