package com.framgia.fpoll.ui.pollcreation.participant;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.creation.CreationRepository;
import com.framgia.fpoll.databinding.FragmentPageParticipantBinding;
import com.framgia.fpoll.ui.poll.PollCreatedFragment;
import com.framgia.fpoll.util.Constant;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantFragment extends Fragment implements ParticipantPollContract.View {
    private ParticipantPollContract.Presenter mPresenter;
    private FragmentPageParticipantBinding mBinding;
    private ProgressDialog mProgressDialog;

    public static ParticipantFragment newInstance(PollItem pollItem) {
        ParticipantFragment participantFragment = new ParticipantFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM, pollItem);
        participantFragment.setArguments(bundle);
        return participantFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page_participant, container, false);
        mPresenter = new ParticipantPresenter(this, CreationRepository.getInstance(getContext()),
            (PollItem) getArguments().getParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM));
        mBinding.setHandler(new ParticipantHandler(mPresenter));
        mBinding.setPresenter((ParticipantPresenter) mPresenter);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.msg_creating_poll));
        mProgressDialog.setCancelable(false);
        return mBinding.getRoot();
    }

    @Override
    public void nextStep() {
        PollCreatedFragment pollCreatedFragment = PollCreatedFragment.getInstance();
        getFragmentManager().beginTransaction()
            .replace(R.id.frame_layout, pollCreatedFragment, null)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void previousStep() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void showCreatePollError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideDialog() {
        mProgressDialog.hide();
    }

    @Override
    public void start() {
    }
}
