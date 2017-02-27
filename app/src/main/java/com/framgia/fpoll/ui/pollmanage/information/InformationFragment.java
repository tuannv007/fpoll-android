package com.framgia.fpoll.ui.pollmanage.information;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentInformationBinding;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends Fragment implements InformationContract.View {
    private FragmentInformationBinding mBinding;
    private InformationContract.Presenter mPresenter;

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false);
        mPresenter = new InformationPresenter(this);
        mBinding.setHandler(new InformationHandler(mPresenter));
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void startUiVoting() {
        startActivity(ManagePollActivity.getManageIntent(getActivity(), ViewpagerType.VOTE));
    }
}
