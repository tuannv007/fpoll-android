package com.framgia.fpoll.ui.votemanager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.databinding.FragmentVoteResultBinding;
import com.framgia.fpoll.ui.votemanager.barchart.BarchartFragment;
import com.framgia.fpoll.ui.votemanager.piechartresult.PieChartFragment;
import com.framgia.fpoll.ui.votemanager.resultvote.TableVoteFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoteResultFragment extends Fragment implements VoteResultContract.View {
    private FragmentVoteResultBinding mBinding;
    private VoteResultContract.Presenter mPresenter;
    private List<ResultItem> mListVoteResult = new ArrayList<>();
    private VoteResultType mVoteResultType = VoteResultType.TABLE;

    public static VoteResultFragment newInstance() {
        return new VoteResultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_vote_result, container, false);
        mPresenter = new VoteResultPresenter(this, mVoteResultType);
        mPresenter.initFragment();
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        setHasOptionsMenu(true);
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
                mVoteResultType = VoteResultType.PIE_CHAR;
                break;
            case R.id.action_bar_chart:
                mVoteResultType = VoteResultType.BAR_CHART;
                break;
            case R.id.action_table:
            default:
                mVoteResultType = VoteResultType.TABLE;
                break;
        }
        mPresenter.updateVoteResultType(mVoteResultType);
        mPresenter.initFragment();
        return super.onOptionsItemSelected(item);
    }

    private void addFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    @Override
    public void addPieChartFragment() {
        addFragment(PieChartFragment.newInstance(mListVoteResult));
    }

    @Override
    public void addBarChartFragment() {
        addFragment(BarchartFragment.newInstance(mListVoteResult));
    }

    @Override
    public void addTableFragment() {
        addFragment(TableVoteFragment.newInstance(mListVoteResult));
    }
}
