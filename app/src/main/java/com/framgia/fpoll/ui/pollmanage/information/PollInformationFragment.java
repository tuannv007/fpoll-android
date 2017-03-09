package com.framgia.fpoll.ui.pollmanage.information;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.databinding.FragmentInformationBinding;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.pollmanage.information.viewoption.PollOptionDialogFragment;
import com.framgia.fpoll.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class PollInformationFragment extends Fragment implements PollInformationContract.View {
    private FragmentInformationBinding mBinding;
    private PollInformationContract.Presenter mPresenter;
    private DataInfoItem mPollInfo;

    public static PollInformationFragment newInstance(DataInfoItem pollInfo) {
        PollInformationFragment fragment = new PollInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.ConstantApi.KEY_POLL_INFO, pollInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false);
        getData();
        mPresenter = new PollInformationPresenter(this);
        mBinding.setHandler(new PollInformationHandler(mPresenter));
        mBinding.setInformation(mPollInfo);
        return mBinding.getRoot();
    }

    public void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) mPollInfo = bundle.getParcelable(Constant.ConstantApi.KEY_POLL_INFO);
    }

    @Override
    public void start() {
    }

    @Override
    public void startUiVoting() {
        startActivity(ManagePollActivity.getManageIntent(getActivity(), ViewpagerType.VOTE, ""));
    }

    @Override
    public void viewOption() {
        if (mPollInfo == null) return;
        showDialogOption();
    }

    @Override
    public void showDialogOption() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DialogFragment optionDialog =
            PollOptionDialogFragment.newInstance(mPollInfo.getPoll().getOptions());
        optionDialog.show(transaction, Constant.TYPE_DIALOG_FRAGMENT);
    }
}
