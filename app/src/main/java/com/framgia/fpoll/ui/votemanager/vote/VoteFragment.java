package com.framgia.fpoll.ui.votemanager.vote;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.VoteItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.remote.voteinfo.VoteInfoRepository;
import com.framgia.fpoll.databinding.FragmentVoteBinding;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.ui.votemanager.vote.TypeItemVote.MULTI_CHOISE;

/**
 * Created by tran.trung.phong on 22/02/2017.
 */
public class VoteFragment extends Fragment implements VoteContract.View {
    private static final String ARGUMENT_VOTE_INFO = "ARGUMENT_VOTE_INFO";
    private FragmentVoteBinding mBinding;
    private ObservableField<VoteAdapter> mAdapter = new ObservableField<>();
    private VoteInfoModel mVoteInfoModel;
    private VoteContract.Presenter mPresenter;

    public static VoteFragment newInstance(VoteInfoModel voteInfo) {
        VoteFragment voteInformationFragment = new VoteFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_VOTE_INFO, voteInfo);
        voteInformationFragment.setArguments(bundle);
        return voteInformationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mVoteInfoModel = (VoteInfoModel) getArguments().getSerializable(ARGUMENT_VOTE_INFO);
        mPresenter = new VotePresenter(this, VoteInfoRepository.getInstance(getContext()));
        mAdapter.set(new VoteAdapter(mPresenter, mVoteInfoModel));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vote, container, false);
        mBinding.setFragment(this);
        mBinding.setPresenter((VotePresenter) mPresenter);
        mBinding.setVoteInfoModel(mVoteInfoModel);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void updateVoteChoice(OptionModel optionModel) {
        optionModel.setChecked(!optionModel.isChecked());
        for (int i = 0; i < mVoteInfoModel.getOptionModels().size(); i++) {
            if (optionModel.getOption().getId() !=
                mVoteInfoModel.getOptionModels().get(i).getOption().getId()) {
                mVoteInfoModel.getOptionModels().get(i).setChecked(false);
            }
        }
    }

    @Override
    public void onSubmitSuccess() {
        //TODO submit vote success
    }

    @Override
    public void onSubmitFailed() {
        //TODO submit vote failed
    }

    public ObservableField<VoteAdapter> getAdapter() {
        return mAdapter;
    }
}
