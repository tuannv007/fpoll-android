package com.framgia.fpoll.ui.pollcreation.participant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.FragmentPageParticipantBinding;
import com.framgia.fpoll.ui.pollcreated.PollCreatedActivity;
import com.framgia.fpoll.widget.FPollProgressDialog;
import com.tokenautocomplete.TokenCompleteTextView;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantFragment extends Fragment implements ParticipantPollContract.View {
    private ParticipantPollContract.Presenter mPresenter;
    private FragmentPageParticipantBinding mBinding;
    private FPollProgressDialog mProgressDialog;
    private PollItem mPoll = new PollItem();

    public static ParticipantFragment newInstance(PollItem pollItem) {
        ParticipantFragment participantFragment = new ParticipantFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        participantFragment.setArguments(bundle);
        return participantFragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getParcelable(BUNDLE_POLL_ITEM) == null) return;
        mPoll = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (mPoll == null) mPoll = new PollItem();
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
        return mBinding.getRoot();
    }

    @Override
    public void startUiPollCreated(PollItem data) {
        startActivity(PollCreatedActivity.getIntent(getActivity(), data));
        getActivity().finish();
    }

    @Override
    public List<String> getMembers() {
        return mBinding.editSendEmail.getObjects();
    }

    @Override
    public void showCreatePollError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new FPollProgressDialog(getActivity());
            mProgressDialog.setCancelable(false);
        }
        if (!mProgressDialog.isShowing()) mProgressDialog.show();
    }

    @Override
    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.hide();
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
