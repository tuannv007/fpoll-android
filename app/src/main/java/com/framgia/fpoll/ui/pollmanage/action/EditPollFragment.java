package com.framgia.fpoll.ui.pollmanage.action;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.FragmentActionBinding;
import com.framgia.fpoll.ui.polledition.ModifyPollActivity;
import com.framgia.fpoll.util.ActivityUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPollFragment extends Fragment implements EditPollContract.View {
    private FragmentActionBinding mBinding;
    private EditPollContract.Presenter mPresenter;

    public static EditPollFragment newInstance() {
        return new EditPollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_action, container, false);
        mPresenter = new EditPollPresenter(this, ManagerRepository.getInstance(getActivity()));
        mBinding.setPresenter((EditPollPresenter) mPresenter);
        mBinding.setHandler(new EditPollHandler(mPresenter));
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void showMessage(String msg) {
        ActivityUtil.showToast(getActivity(), msg);
    }

    @Override
    public void startModifyPoll() {
        Intent intent = new Intent(getActivity(), ModifyPollActivity.class);
        startActivity(intent);
    }
}
