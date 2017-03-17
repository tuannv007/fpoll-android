package com.framgia.fpoll.ui.history.pollhistory;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.FragmentPollHistoryBinding;
import com.framgia.fpoll.ui.history.PollHistoryType;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;
import com.framgia.fpoll.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PollHistoryFragment extends Fragment implements PollHistoryContract.View {
    private FragmentPollHistoryBinding mBinding;
    private List<HistoryPoll> mListPollHistory = new ArrayList<>();
    private ObservableField<PollHistoryAdapter> mAdapter = new ObservableField<>();
    private ObservableBoolean mLoadFinish = new ObservableBoolean();
    private PollHistoryContract.Presenter mPresenter;
    private PollHistoryType mPollHistoryType;

    public static PollHistoryFragment getInstance(PollHistoryType typeHistory) {
        PollHistoryFragment fragment = new PollHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.BundleConstant.BUNDLE_TYPE_HISTORY, typeHistory);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPollHistoryType = (PollHistoryType) bundle
                .getSerializable(Constant.BundleConstant.BUNDLE_TYPE_HISTORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_poll_history, container, false);
        getDataFromActivity();
        mPresenter = new PollHistoryPresenter(this, mPollHistoryType,
            ManagerRepository.getInstance(getActivity()),
            SharePreferenceUtil.getIntances(getActivity()));
        mBinding.setPresenter((PollHistoryPresenter) mPresenter);
        mBinding.setFragment(this);
        mAdapter.set(new PollHistoryAdapter(mListPollHistory, mPollHistoryType, mPresenter));
        mPresenter.getData();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void setPollHistory(List<HistoryPoll> pollHistories) {
        mListPollHistory.clear();
        mListPollHistory.addAll(pollHistories);
        mAdapter.get().update(mListPollHistory);
    }

    @Override
    public void clickOpenManagePoll(HistoryPoll pollHistoryItem) {
        startActivity(ManagePollActivity.getManageIntent(getActivity(), ViewpagerType.MANAGE, ""));
    }

    @Override
    public void clickReopenPoll(HistoryPoll pollHistoryItem) {
        // TODO: 2/23/2017 handler click reopen  poll
    }

    @Override
    public void showMessage(int res) {
        ActivityUtil.showToast(getActivity(), res);
    }

    @Override
    public void setLoadingTrue() {
        mLoadFinish.set(true);
    }

    @Override
    public void setLoadingFalse() {
        mLoadFinish.set(false);
    }

    public ObservableField<PollHistoryAdapter> getAdapter() {
        return mAdapter;
    }

    public ObservableBoolean getLoadFinish() {
        return mLoadFinish;
    }
}
