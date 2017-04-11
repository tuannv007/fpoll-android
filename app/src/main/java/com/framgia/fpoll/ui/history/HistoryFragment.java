package com.framgia.fpoll.ui.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.databinding.FragmentHistoryBinding;
import com.framgia.fpoll.ui.history.pollhistory.PollHistoryFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements HistoryContract.View {
    private HistoryContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentHistoryBinding binding = FragmentHistoryBinding.inflate(inflater, container, false);
        binding.setFragment(this);
        mPresenter = new HistoryPresenter(this);
        return binding.getRoot();
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

    public void clearData() {
        int size = mAdapter.getCount();
        for (int i = 0; i < size; i++) {
            Fragment fragment = mAdapter.getItem(i);
            if (fragment != null && fragment instanceof PollHistoryFragment) {
                ((PollHistoryFragment) fragment).clearData();
            }
        }
    }

    public void setTitle(PollHistoryType type) {
        switch (type) {
            case INITIATE:
                getActivity().setTitle(R.string.title_poll_initiate);
                break;
            case PARTICIPATE:
                getActivity().setTitle(R.string.title_poll_participate);
                break;
            case CLOSE:
                getActivity().setTitle(R.string.title_poll_close);
                break;
            default:
                break;
        }
    }

    public void updatePollHistory(HistoryPoll poll) {
        int size = mAdapter.getCount();
        for (int i = 0; i < size; i++) {
            Fragment fragment = mAdapter.getItem(i);
            if (fragment != null && fragment instanceof PollHistoryFragment) {
                ((PollHistoryFragment) fragment).updatePollHistory(poll);
            }
        }
    }
}
