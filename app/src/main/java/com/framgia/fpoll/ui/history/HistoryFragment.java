package com.framgia.fpoll.ui.history;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.databinding.FragmentHistoryBinding;
import com.framgia.fpoll.ui.pollhistory.PollHistoryFragment;
import com.framgia.fpoll.ui.pollmanage.action.ActionFragment;
import com.framgia.fpoll.ui.pollmanage.information.InformationFragment;
import com.framgia.fpoll.ui.pollmanage.result.ResultFragment;
import com.framgia.fpoll.ui.vote.VoteFragment;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_VIEW_PAGE_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements HistoryContract.View {
    private FragmentHistoryBinding mBinding;
    private HistoryContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;
    private ViewpagerType mViewpagerType;

    public static HistoryFragment newInstance(ViewpagerType type) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_PAGE_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        mBinding.setFragment(this);
        getDataFromActivity();
        mPresenter = new HistoryPresenter(this, mViewpagerType);
        mPresenter.getAdapterType();
        return mBinding.getRoot();
    }

    @Override
    public void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mViewpagerType = (ViewpagerType) bundle.getSerializable(BUNDLE_VIEW_PAGE_TYPE);
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void initAdapterHistory() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.INITIATE));
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.PARTICIPATE));
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.CLOSE));
        String[] titles = getActivity().getResources().getStringArray(R.array.array_history);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    public void initAdapterManage() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(InformationFragment.newInstance());
        fragments.add(ResultFragment.newInstance());
        fragments.add(ActionFragment.newInstance());
        String[] titles = getActivity().getResources().getStringArray(R.array.array_manage);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    @Override
    public void initAdapterVote() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(VoteFragment.newIntance());
        fragments.add(InformationFragment.newInstance());
        fragments.add(ResultFragment.newInstance());
        String[] titles = getActivity().getResources().getStringArray(R.array.array_vote);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
