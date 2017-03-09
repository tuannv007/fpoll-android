package com.framgia.fpoll.ui.pollmanage.information.viewoption;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.voteinfo.Option;
import com.framgia.fpoll.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanbg on 3/9/17.
 */
public class PollOptionDialogFragment extends DialogFragment {
    private com.framgia.fpoll.databinding.FragmentViewOptionBinding mBinding;
    private ObservableField<PollInfoAdapter> mAdapter = new ObservableField<>();
    private List<Option> mPollOptionItemList = new ArrayList<>();

    public static PollOptionDialogFragment newInstance(List<Option> option) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constant.ConstantApi.KEY_POLL_OPTION,
            (ArrayList<? extends Parcelable>) option);
        PollOptionDialogFragment fragment = new PollOptionDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_option, container, false);
        mBinding.setFragment(this);
        getDataFromIntent();
        mAdapter.set(new PollInfoAdapter(mPollOptionItemList));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getString(R.string.title_option));
    }

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mPollOptionItemList = bundle.getParcelableArrayList(Constant.ConstantApi.KEY_POLL_OPTION);
    }

    public ObservableField<PollInfoAdapter> getAdapter() {
        return mAdapter;
    }
}
