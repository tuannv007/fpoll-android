package com.framgia.fpoll.ui.vote;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.VoteItem;
import com.framgia.fpoll.databinding.FragmentVoteBinding;
import com.framgia.fpoll.util.DataBindingUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteFragment extends Fragment {
    private FragmentVoteBinding mBinding;
    private ObservableField<VoteAdapter> mAdapter = new ObservableField<>();
    private List<VoteItem> mVoteItems = new ArrayList<>();

    public static VoteFragment getIntance() {
        return new VoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vote, container, false);
        mBinding.setFragment(this);
        mAdapter.set(new VoteAdapter(mVoteItems));
        return mBinding.getRoot();
    }

    public ObservableField<VoteAdapter> getAdapter() {
        return mAdapter;
    }
}
