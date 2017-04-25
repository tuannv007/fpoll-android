package com.framgia.fpoll.ui.feedback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.feedback.FeedbackRepository;
import com.framgia.fpoll.databinding.FragmentFeedbackBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.SharePreferenceUtil;
import com.framgia.fpoll.widget.FPollProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements FeedbackContract.View {
    private FPollProgressDialog mProgressDialog;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentFeedbackBinding binding =
                FragmentFeedbackBinding.inflate(inflater, container, false);
        FeedbackContract.Presenter presenter =
                new FeedbackPresenter(this, FeedbackRepository.getInstance(getActivity()),
                        SharePreferenceUtil.getIntances(getActivity()));
        binding.setPresenter((FeedbackPresenter) presenter);
        binding.setHandler(new FeedbackHandler(presenter));
        return binding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new FPollProgressDialog(getActivity());
        }
        if (!mProgressDialog.isShowing()) mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
    }

    @Override
    public void showMessage(int resString) {
        ActivityUtil.showToast(getActivity(), resString);
    }

    @Override
    public void sendFeedbackSuccess() {
        ActivityUtil.showToast(getActivity(), R.string.msg_send_feedback_success);
    }
}
