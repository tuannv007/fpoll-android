package com.framgia.fpoll.ui.polloption;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentPageOptionBinding;

/**
 * Created by framgia on 22/02/2017.
 */
public class OptionPollFragment extends Fragment implements OptionPollContract.View {
    private FragmentPageOptionBinding mBinding;
    private OptionPollContract.Presenter mPresenter;

    public static OptionPollFragment newInstance() {
        return new OptionPollFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page_option, container, false);
        mPresenter = new OptionPresenter(this);
        mBinding.setHandler(new OptionHandler(mPresenter));
        mBinding.setPresenter((OptionPresenter) mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void nextStep() {
        // TODO: next step
    }

    @Override
    public void previousStep() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void start() {
    }
}
