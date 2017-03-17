package com.framgia.fpoll.ui.polledition;

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
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(EditInforFragment.newInstance());
        fragments.add(EditOptionFragment.newInstance(new PollItem()));
        fragments.add(EditSettingFragment.newInstance(new PollItem()));
        String[] titles = getResources().getStringArray(R.array.array_vote);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
