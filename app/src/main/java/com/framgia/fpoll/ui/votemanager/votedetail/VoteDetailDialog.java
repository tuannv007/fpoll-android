package com.framgia.fpoll.ui.votemanager.votedetail;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.databinding.DialogVoteDetailBinding;

import java.util.List;

/**
 * Created by anhtv on 16/03/2017.
 */
public class VoteDetailDialog extends DialogFragment {
    private static final String ARGUMENT_OPTION = "ARGUMENT_OPTION";
    private Option mOption;
    private VoteDetailAdapter mDetailAdapter;
    private LayoutInflater mInflater;
    private VoteDetailContract.Presenter mPresenter;

    public static VoteDetailDialog newInstance(Option option) {
        VoteDetailDialog detailDialog = new VoteDetailDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_OPTION, option);
        detailDialog.setArguments(bundle);
        return detailDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mOption = getArguments().getParcelable(ARGUMENT_OPTION);
        }
        mPresenter = new VoteDetailPresenter();
        List<ParticipantVotes> participantVotesList = mPresenter.getListProfile(mOption);
        mDetailAdapter = new VoteDetailAdapter(mOption, participantVotesList);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mInflater == null) mInflater = LayoutInflater.from(getActivity());
        DialogVoteDetailBinding binding = DataBindingUtil
            .inflate(mInflater, R.layout.dialog_vote_detail, null, false);
        binding.setDialog(this);
        binding.setOption(mOption);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(binding.getRoot());
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        return dialog;
    }

    public VoteDetailAdapter getAdapter() {
        return mDetailAdapter;
    }
}
