package com.framgia.fpoll.ui.polledition.editsetting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.databinding.FragmentEditSettingBinding;
import com.framgia.fpoll.util.Constant;

/**
 * Created by framgia on 17/03/2017.
 */
public class EditSettingFragment extends Fragment implements EditSettingContract.View {
    private FragmentEditSettingBinding mBinding;
    private EditSettingContract.Presenter mPresenter;
    private PollItem mPollItem;

    public static EditSettingFragment newInstance(PollItem pollItem) {
        EditSettingFragment editSettingFragment = new EditSettingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM, pollItem);
        editSettingFragment.setArguments(bundle);
        return editSettingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_setting, container, false);
        mPollItem = getArguments().getParcelable(Constant.BundleConstant.BUNDLE_POLL_ITEM);
        mPresenter = new EditSettingPresenter(this, mPollItem);
        mBinding.setHandler(new EditSettingHandler(mPresenter));
        mBinding.setPresenter((EditSettingPresenter) mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void nextStep() {
    }

    @Override
    public void previousStep() {
    }
}
