package com.framgia.fpoll.ui.pollmanage.action;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentActionBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionFragment extends Fragment {
    private FragmentActionBinding mBinding;

    public static ActionFragment newInstance() {
        return new ActionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_action, container, false);
        return mBinding.getRoot();
    }
}
