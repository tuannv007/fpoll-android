package com.framgia.fpoll.ui.votemanager.barchart;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.databinding.BarChartGraphBinding;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_LIST_RESULT_ITEM;

/**
 * Created by tran.trung.phong on 24/02/2017.
 */
public class BarchartFragment extends DialogFragment implements BarcharContract.View {
    private BarChartGraphBinding mBinding;
    private ObservableList<ResultItem> mItems = new ObservableArrayList<>();
    private BarcharContract.Presenter mPresenter;

    public static BarchartFragment newInstance(List<ResultItem> items) {
        BarchartFragment fragment = new BarchartFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_LIST_RESULT_ITEM, (ArrayList<ResultItem>) items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.bar_chart_graph, container, false);
        getData();
        mPresenter = new BarchartPresenter(this, mItems);
        mPresenter.getBarData();
        mBinding.setBarChart(this);
        mBinding.setPresenter((BarchartPresenter) mPresenter);
        return mBinding.getRoot();
    }

    @Override
    public void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            List<ResultItem> resultItems = bundle.getParcelableArrayList(BUNDLE_LIST_RESULT_ITEM);
            mItems.addAll(resultItems);
        }
    }

    @Override
    public void start() {
        getDialog().setTitle(getString(R.string.title_bar_chart));
    }

    public ObservableList<ResultItem> getItems() {
        return mItems;
    }
}
