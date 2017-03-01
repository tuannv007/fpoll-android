package com.framgia.fpoll.ui.votemanager.information;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentPollInfoBinding;

/**
 * Created by Nhahv0902 on 2/28/2017.
 * <.
 */
public class VoteInformationFragment extends Fragment {
    private FragmentPollInfoBinding mBinding;

    public static VoteInformationFragment newInstance() {
        return new VoteInformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_poll_info, container, false);
        return mBinding.getRoot();
    }
}
