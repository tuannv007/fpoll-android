package com.framgia.fpoll.ui.pollparticipant;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.databinding.FragmentPageParticipantBinding;
import com.framgia.fpoll.ui.pollcreated.PollCreatedFragment;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantFragment extends Fragment implements ParticipantPollContract.View {
    private ParticipantPollContract.Presenter mPresenter;
    private FragmentPageParticipantBinding mBinding;

    public static ParticipantFragment newInstance() {
        return new ParticipantFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page_participant, container, false);
        mPresenter = new ParticipantPresenter(this);
        mBinding.setHandler(new ParticipantHandler(mPresenter));
        mBinding.setPresenter((ParticipantPresenter) mPresenter);
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
    public void start() {
    }
}
