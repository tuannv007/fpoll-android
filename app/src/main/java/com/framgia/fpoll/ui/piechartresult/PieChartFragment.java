package com.framgia.fpoll.ui.piechartresult;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.databinding.FragmentPieChartBinding;
import com.framgia.fpoll.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 24/02/2017.
 */
public class PieChartFragment extends Fragment
    implements PieChartContract.View {
    public static final int TEXT_SIZE = 20;
    private FragmentPieChartBinding mBinding;
    private PieChartPresenter mPresenter;

    public static PieChartFragment newInstance(List<ResultItem> list) {
        PieChartFragment pieChartFragment = new PieChartFragment();
        Bundle argument = new Bundle();
        argument
            .putParcelableArrayList(Constant.BundleConstant.BUNDLE_LIST_RESULT, (ArrayList) list);
        pieChartFragment.setArguments(argument);
        return pieChartFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pie_chart, container,
            false);
        mPresenter = new PieChartPresenter(this);
        mPresenter.setUpPieData((ArrayList) getArguments()
            .getParcelableArrayList(Constant.BundleConstant.BUNDLE_LIST_RESULT));
        mBinding.setPresenter(mPresenter);
        mBinding.pieChart.setDescription(Constant.DataConstant.DATA_NO_TITLE);
        mBinding.pieChart.setUsePercentValues(true);
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }
}
