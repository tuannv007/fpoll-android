package com.framgia.fpoll.ui.pollmanage.information.pollsetting;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Setting;
import com.framgia.fpoll.databinding.DialogFragmentPollSettingBinding;
import com.framgia.fpoll.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/9/17.
 */
public class PollSettingDialogFragment extends DialogFragment {
    private DialogFragmentPollSettingBinding mBinding;
    private ObservableField<PollSettingAdapter> mAdapter = new ObservableField<>();
    private List<Setting> mSettingList = new ArrayList<>();

    public static PollSettingDialogFragment newInstance(List<Setting> settings) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constant.ConstantApi.KEY_POLL_SETTING,
            (ArrayList<? extends Parcelable>) settings);
        PollSettingDialogFragment fragment = new PollSettingDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.dialog_fragment_poll_setting, container, false);
        mBinding.setFragment(this);
        getDataFromIntent();
        mAdapter.set(new PollSettingAdapter(mSettingList));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getString(R.string.title_setting));
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mSettingList = bundle.getParcelableArrayList(Constant.ConstantApi.KEY_POLL_SETTING);
    }

    public ObservableField<PollSettingAdapter> getAdapter() {
        return mAdapter;
    }
}
