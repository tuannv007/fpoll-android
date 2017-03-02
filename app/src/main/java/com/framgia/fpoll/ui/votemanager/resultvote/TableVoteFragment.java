package com.framgia.fpoll.ui.votemanager.resultvote;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.ResultItem;
import com.framgia.fpoll.databinding.FragmentTableVoteBinding;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_LIST_RESULT_ITEM;

/**
 * Created by tran.trung.phong on 23/02/2017.
 * <></>
 */
public class TableVoteFragment extends Fragment {
    private FragmentTableVoteBinding mBinding;
    private List<ResultItem> mResultItems = new ArrayList<>();
    private ObservableField<TableVoteAdapter> mAdapter = new ObservableField<>();

    public static TableVoteFragment newInstance(List<ResultItem> resultItems) {
        TableVoteFragment fragment = new TableVoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_LIST_RESULT_ITEM, (ArrayList<ResultItem>) resultItems);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_vote, container,
            false);
        mBinding.setFragment(this);
        mAdapter.set(new TableVoteAdapter(mResultItems));
        return mBinding.getRoot();
    }

    public ObservableField<TableVoteAdapter> getAdapter() {
        return mAdapter;
    }
}
