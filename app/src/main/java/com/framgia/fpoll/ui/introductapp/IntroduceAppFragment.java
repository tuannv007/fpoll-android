package com.framgia.fpoll.ui.introductapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.IntroduceItem;
import com.framgia.fpoll.data.source.local.IntroduceRepository;
import com.framgia.fpoll.data.source.local.introduce.IntroduceLocalDataSource;
import com.framgia.fpoll.databinding.FragmentIntroduceBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;

import java.util.List;

/**
 * Created by tuanbg on 2/22/17.
 */
public class IntroduceAppFragment extends Fragment
    implements IntroduceAppContract.View {
    private FragmentIntroduceBinding mBinding;
    private ObservableField<IntroduceAdapter> mAdapter = new ObservableField<>();
    private IntroduceAppPresenter mPresenter;

    public static IntroduceAppFragment newInstance() {
        return new IntroduceAppFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_introduce, container, false);
        mPresenter = new IntroduceAppPresenter(this, IntroduceRepository.getInstance(getActivity()));
        start();
        mBinding.setHandler(new IntroduceHandlerAction(mPresenter));
        mBinding.setFragment(this);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mPresenter.getData();
    }

    public ObservableField<IntroduceAdapter> getAdapter() {
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
        mAdapter.set(new IntroduceAdapter(list));
    }

    @Override
    public void updateIntroduceError() {
        ActivityUtil.showToast(getActivity(), R.string.msg_not_load_item);
    }

    public int typeLayoutGrid() {
        return Constant.TYPE_LAYOUT_GRID_TWO;
    }
}
