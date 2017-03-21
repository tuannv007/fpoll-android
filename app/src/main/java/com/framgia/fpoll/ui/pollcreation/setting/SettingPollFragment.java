package com.framgia.fpoll.ui.pollcreation.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.FragmentPageSettingBinding;
import com.framgia.fpoll.ui.pollcreation.participant.ParticipantFragment;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 23/02/2017.
 */
public class SettingPollFragment extends Fragment implements SettingPollContract.View {
    private FragmentPageSettingBinding mBinding;
    private SettingPollContract.Presenter mPresenter;
    private PollItem mPoll = new PollItem();

    public static SettingPollFragment newInstance(PollItem pollItem) {
        SettingPollFragment settingPollFragment = new SettingPollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        settingPollFragment.setArguments(bundle);
        return settingPollFragment;
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
        mBinding = FragmentPageSettingBinding.inflate(inflater, container, false);
        getDataFromActivity();
        mPresenter = new SettingPresenter(this, mPoll);
        mBinding.setHandler(new SettingHandler(mPresenter));
        mBinding.setPresenter((SettingPresenter) mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void nextStep() {
        getFragmentManager().beginTransaction()
            .add(R.id.frame_layout, ParticipantFragment.newInstance(mPoll), null)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void previousStep() {
        getFragmentManager().popBackStack();
    }
}
