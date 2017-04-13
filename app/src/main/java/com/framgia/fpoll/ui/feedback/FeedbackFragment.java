package com.framgia.fpoll.ui.feedback;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.source.remote.feedback.FeedbackRepository;
import com.framgia.fpoll.databinding.FragmentFeedbackBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.widget.FPollProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackFragment extends Fragment implements FeedbackContract.View {
    private FragmentFeedbackBinding mBinding;
    private FeedbackContract.Presenter mPresenter;
    private FPollProgressDialog mProgressDialog;

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container, false);
        mPresenter = new FeedbackPresenter(this, FeedbackRepository.getInstance(getActivity()));
        mBinding.setPresenter((FeedbackPresenter) mPresenter);
        mBinding.setHandler(new FeedbackHandler(mPresenter));
        return mBinding.getRoot();
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
