package com.framgia.fpoll.ui.introduction;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.IntroduceRepository;
import com.framgia.fpoll.databinding.ActivityIntroduceBinding;
import com.framgia.fpoll.util.ActivityUtil;

import java.util.List;

public class IntroduceActivity extends AppCompatActivity implements IntroduceAppContract.View {
    private ActivityIntroduceBinding mBinding;
    private ObservableField<ViewPageAdapterAuto> mAdapter = new ObservableField<>();
    private IntroduceAppPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_introduce);
        mPresenter = new IntroduceAppPresenter(this, IntroduceRepository.getInstance(this));
        start();
        mBinding.setHandler(new IntroduceHandlerAction(mPresenter));
        mBinding.setActivity(this);
    }

    @Override
    public void start() {
        mPresenter.getData();
    }

    public ObservableField<ViewPageAdapterAuto> getAdapter() {
        return mAdapter;
    }

    @Override
    public void openFaceBook() {
        Intent browserIntent =
            new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.title_facebook_company)));
        startActivity(browserIntent);
    }

    @Override
    public void openGitHub() {
        Intent browserIntent =
            new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.title_github_company)));
        startActivity(browserIntent);
    }

    @Override
    public void openLikeDin() {
        Intent browserIntent =
            new Intent(Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.title_linkedin_company)));
        startActivity(browserIntent);
    }

    @Override
    public void updateIntroduceView(List<IntroduceItem> list) {
        mAdapter.set(new ViewPageAdapterAuto(this, list));
    }

    @Override
    public void updateIntroduceError() {
        ActivityUtil.showToast(this, R.string.msg_not_load_item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.viewPager.stopAutoScroll();
    }
}
