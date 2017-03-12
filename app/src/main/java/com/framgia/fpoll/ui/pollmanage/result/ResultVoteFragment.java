package com.framgia.fpoll.ui.pollmanage.result;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.source.remote.resultvote.ResultVoteDataRepository;
import com.framgia.fpoll.databinding.FragmentVoteResultBinding;
import com.framgia.fpoll.networking.ResponseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultVoteFragment extends Fragment implements ResultVoteContract.View {
    private FragmentVoteResultBinding mBinding;
    private ResultVoteContract.Presenter mPresenter;
    private ObservableField<ResultVoteAdapter> mAdapter = new ObservableField<>();
    private List<ResultVoteItem.Result> mListResultVote = new ArrayList<>();
    private String mToken;

    public static ResultVoteFragment newInstance(String token) {
        return new ResultVoteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vote_result, container, false);
        mPresenter =
            new ResultVotePresenter(ResultVoteDataRepository.getInstance(getActivity()), this);
        if (TextUtils.isEmpty(mToken)) mPresenter.getAllData(mToken);
        mBinding.setFragment(this);
        mBinding.setHandler(new ResultActionHandler(mPresenter));
        return mBinding.getRoot();
    }

    @Override
    public void onSuccess(ResponseItem<ResultVoteItem> resultVoteList) {
        mListResultVote.addAll(resultVoteList.getData().getResults());
        mAdapter.set(new ResultVoteAdapter(mListResultVote));
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void start() {
    }

    public ObservableField<ResultVoteAdapter> getAdapter() {
        return mAdapter;
    }
}
