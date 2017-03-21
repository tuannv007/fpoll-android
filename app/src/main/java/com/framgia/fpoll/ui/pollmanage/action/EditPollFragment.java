package com.framgia.fpoll.ui.pollmanage.action;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.FragmentActionBinding;
import com.framgia.fpoll.ui.polledition.ModifyPollActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPollFragment extends Fragment implements EditPollContract.View {
    private FragmentActionBinding mBinding;
    private EditPollContract.Presenter mPresenter;
    private DataInfoItem mDataInfoItem;

    public static EditPollFragment newInstance(DataInfoItem dataInfoItem) {
        EditPollFragment editPollFragment = new EditPollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM, dataInfoItem);
        editPollFragment.setArguments(bundle);
        return editPollFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_action, container, false);
        mPresenter = new EditPollPresenter(this, ManagerRepository.getInstance(getActivity()));
        mBinding.setPresenter((EditPollPresenter) mPresenter);
        mBinding.setHandler(new EditPollHandler(mPresenter));
        mDataInfoItem = getArguments().getParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM);
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
        intent.putExtra(Constant.BundleConstant.BUNDLE_POLL_ITEM, mDataInfoItem);
        startActivity(intent);
    }
}
