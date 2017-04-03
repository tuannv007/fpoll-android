package com.framgia.fpoll.ui.votemanager.result;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.databinding.FragmentLinkVoteResultBinding;
import com.framgia.fpoll.ui.votemanager.VoteResultType;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;
import com.framgia.fpoll.ui.votemanager.votedetail.VoteDetailDialog;

/**
 * Created by anhtv on 15/03/2017.
 */
public class LinkVoteResultFragment extends Fragment implements LinkVoteResultContract.View {
    private static final String ARGUMENT_VOTE_INFO = "ARGUMENT_VOTE_INFO";
    private VoteInfoModel mVoteInfoModel;
    private LinkVoteResultContract.Presenter mPresenter;
    private ResultAdapter mResultAdapter;
    private ObservableField<VoteResultType> mVoteResultType = new ObservableField<>();

    public static LinkVoteResultFragment newInstance(VoteInfoModel voteInfoModel) {
        LinkVoteResultFragment linkVoteResultFragment = new LinkVoteResultFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_VOTE_INFO, voteInfoModel);
        linkVoteResultFragment.setArguments(bundle);
        return linkVoteResultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mVoteInfoModel = getArguments().getParcelable(ARGUMENT_VOTE_INFO);
        mPresenter = new LinkVoteResultPresenter(this);
        mVoteResultType.set(VoteResultType.TABLE);
        mResultAdapter =
            new ResultAdapter(mVoteInfoModel, (LinkVoteResultPresenter) mPresenter);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLinkVoteResultBinding binding =
            FragmentLinkVoteResultBinding.inflate(inflater, container, false);
        binding.setFragment(this);
        binding.setVoteInfoModel(mVoteInfoModel);
        binding.layoutTableResult.setFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.vote_result_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pie_chart:
                mVoteResultType.set(VoteResultType.PIE_CHAR);
                break;
            case R.id.action_bar_chart:
                mVoteResultType.set(VoteResultType.BAR_CHART);
                break;
            case R.id.action_table:
                mVoteResultType.set(VoteResultType.TABLE);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetail(Option option) {
        VoteDetailDialog.newInstance(option).show(getChildFragmentManager(), "");
    }

    @Override
    public void start() {
    }

    public ResultAdapter getAdapter() {
        return mResultAdapter;
    }

    public ObservableField<VoteResultType> getVoteResultType() {
        return mVoteResultType;
    }
}
