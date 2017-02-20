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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements HistoryContract.View {
    private FragmentHistoryBinding mBinding;
    private HistoryContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        mBinding.setFragment(this);
        mPresenter = new HistoryPresenter(this);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.INITIATE));
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.PARTICIPATE));
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.CLOSE));
        String[] titles = getActivity().getResources().getStringArray(R.array.array_history);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }
}
