package com.framgia.fpoll.ui.pollcreation.participant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.FragmentPageParticipantBinding;
import com.framgia.fpoll.ui.pollcreation.PollCreationActivity;
import com.tokenautocomplete.TokenCompleteTextView;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_EVENT_SWITCH_UI;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantFragment extends Fragment implements ParticipantPollContract.View {
    private ParticipantPollContract.Presenter mPresenter;
    private FragmentPageParticipantBinding mBinding;
    private ProgressDialog mProgressDialog;
    private PollItem mPoll = new PollItem();
    private PollCreationActivity.EventSwitchUI mListener;

    public static ParticipantFragment newInstance(PollItem pollItem,
                                                  PollCreationActivity.EventSwitchUI event) {
        ParticipantFragment participantFragment = new ParticipantFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        bundle.putParcelable(BUNDLE_EVENT_SWITCH_UI, event);
        participantFragment.setArguments(bundle);
        return participantFragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) return;
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (mPoll == null) mPoll = new PollItem();
        if (bundle.getParcelable(BUNDLE_EVENT_SWITCH_UI) != null) {
            mListener = bundle.getParcelable(BUNDLE_EVENT_SWITCH_UI);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentPageParticipantBinding.inflate(inflater, container, false);
        getDataFromActivity();
        mPresenter =
            new ParticipantPresenter(this, PollRepository.getInstance(getContext()), mPoll);
        mBinding.setPresenter((ParticipantPresenter) mPresenter);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.msg_creating_poll));
        mProgressDialog.setCancelable(false);
        return mBinding.getRoot();
    }

    @Override
    public void startUiPollCreated() {
        if (mListener != null) mListener.startUiPollCreated();
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
        mBinding.editSendEmail.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Delete);
    }

    public void createPoll() {
        if (mPresenter == null) return;
        mPresenter.createPoll();
    }
}
