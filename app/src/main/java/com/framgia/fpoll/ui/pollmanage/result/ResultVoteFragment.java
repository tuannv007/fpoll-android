package com.framgia.fpoll.ui.pollmanage.result;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import static com.framgia.fpoll.util.Constant.ConstantApi.KEY_TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultVoteFragment extends Fragment implements ResultVoteContract.View {
    private FragmentVoteResultBinding mBinding;
    private ResultVoteContract.Presenter mPresenter;
    private ObservableField<ResultVoteAdapter> mAdapter = new ObservableField<>();
    private List<ResultVoteItem.Result> mListResultVote = new ArrayList<>();
    private String mToken ;

    public static ResultVoteFragment newInstance(String token) {
        ResultVoteFragment resultVoteFragment = new ResultVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TOKEN, token);
        resultVoteFragment.setArguments(bundle);
        return resultVoteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vote_result, container, false);
        getDataFromIntent();
        mPresenter =
            new ResultVotePresenter(ResultVoteDataRepository.getInstance(getActivity()), this);
        mBinding.setFragment(this);
        mBinding.setHandler(new ResultActionHandler(mPresenter));
        mPresenter.getAllData(mToken);
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

    public void getDataFromIntent() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mToken = bundle.getString(KEY_TOKEN);
    }

    public ObservableField<ResultVoteAdapter> getAdapter() {
        return mAdapter;
    }
}
