package com.framgia.fpoll.ui.pollcreation.setting;

import android.databinding.DataBindingUtil;
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
import com.framgia.fpoll.util.Constant;

/**
 * Created by framgia on 23/02/2017.
 */
public class SettingPollFragment extends Fragment implements SettingPollContract.View {
    private FragmentPageSettingBinding mBinding;
    private SettingPollContract.Presenter mPresenter;

    public static SettingPollFragment newInstance(PollItem pollItem) {
        SettingPollFragment settingPollFragment = new SettingPollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM, pollItem);
        settingPollFragment.setArguments(bundle);
        return settingPollFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_page_setting, container, false);
        mPresenter = new SettingPresenter(this);
        mBinding.setHandler(new SettingHandler(mPresenter));
        mBinding.setPresenter((SettingPresenter) mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void nextStep() {
        PollItem pollItem = getArguments().getParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM);
        getFragmentManager().beginTransaction()
            .add(R.id.frame_layout, ParticipantFragment.newInstance(pollItem), null)
            .addToBackStack(null)
            .commit();
    }

    @Override
    public void previousStep() {
        getFragmentManager().popBackStack();
    }
}
