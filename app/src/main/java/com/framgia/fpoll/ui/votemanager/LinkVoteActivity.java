package com.framgia.fpoll.ui.votemanager;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.voteinfo.Poll;
import com.framgia.fpoll.data.model.voteinfo.VoteInfo;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRemoteDataSource;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.ActivityLinkVoteBinding;
import com.framgia.fpoll.ui.history.ViewPagerAdapter;
import com.framgia.fpoll.ui.votemanager.information.VoteInformationFragment;
import com.framgia.fpoll.ui.votemanager.itemmodel.ItemStatus;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.ui.votemanager.vote.VoteFragment;

import java.util.ArrayList;
import java.util.List;

public class LinkVoteActivity extends AppCompatActivity implements LinkVoteContract.View {
    private ActivityLinkVoteBinding mBinding;
    private LinkVoteContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;
    private VoteInfoModel mVoteInfoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_link_vote);
        mBinding.setActivity(this);
        mPresenter = new LinkVotePresenter(this, VoteInfoRepository.getInstance(this));
        //TODO get link vote token by intent
        String token = "";
        mPresenter.getVoteInfo(token);
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
        mVoteInfoModel.setItemStatus(ItemStatus.AVAILABLE);
        mVoteInfoModel.setVoteInfo(voteInfo);
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
        fragments.add(VoteFragment.newIntance());
        fragments.add(VoteInformationFragment.newInstance(mVoteInfoModel));
        fragments.add(VoteResultFragment.newInstance());
        String[] titles = getResources().getStringArray(R.array.array_vote);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
