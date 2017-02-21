package com.framgia.fpoll.ui.pollmanage.result;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentPollHistoryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {
    private FragmentPollHistoryBinding mBinding;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_poll_history, container, false);
        return mBinding.getRoot();
    }
}
