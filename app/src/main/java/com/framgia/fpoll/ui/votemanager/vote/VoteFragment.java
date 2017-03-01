package com.framgia.fpoll.ui.votemanager.vote;

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

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.ui.votemanager.vote.TypeItemVote.MULTI_CHOISE;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteFragment extends Fragment implements VoteContract.View {
    private FragmentVoteBinding mBinding;
    private ObservableField<VoteAdapter> mAdapter = new ObservableField<>();
    private List<VoteItem> mVoteItems = new ArrayList<>();
    private TypeItemVote mTypeItemVote;
    private VoteContract.Presenter mPresenter;

    public static VoteFragment newIntance() {
        return new VoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vote, container, false);
        mBinding.setFragment(this);
        mPresenter = new VotePresenter(this);
        mTypeItemVote = MULTI_CHOISE;
        mAdapter.set(new VoteAdapter(mVoteItems, mPresenter, mTypeItemVote));
        return mBinding.getRoot();
    }

    public ObservableField<VoteAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void start() {
    }

    @Override
    public void updateChoiceItem(VoteItem voteItem) {
        mAdapter.get().updateVoteItem(voteItem);
    }
}
