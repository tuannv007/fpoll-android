package com.framgia.fpoll.ui.pollcreation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentCreatePollBinding;

public class CreatePollFragment extends Fragment {
    private FragmentCreatePollBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_poll, container, false);
        return mBinding.getRoot();
    }
}
