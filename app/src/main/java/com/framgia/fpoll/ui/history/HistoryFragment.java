package com.framgia.fpoll.ui.history;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.databinding.FragmentHistoryBinding;
import com.framgia.fpoll.ui.history.pollhistory.PollHistoryFragment;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_HISTORY;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_TOKEN;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_VIEW_PAGE_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements HistoryContract.View {
    private FragmentHistoryBinding mBinding;
    private HistoryContract.Presenter mPresenter;
    private ViewPagerAdapter mAdapter;
    private ViewpagerType mViewpagerType;
    private ObservableField<DataInfoItem> mPollInfo = new ObservableField<>();
    private String mToken;

    public static HistoryFragment newInstance(ViewpagerType type, DataInfoItem dataInfoItemList,
            String token) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_VIEW_PAGE_TYPE, type);
        bundle.putString(BUNDLE_TOKEN, token);
        bundle.putParcelable(BUNDLE_HISTORY, dataInfoItemList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        getDataFromActivity();
        mBinding.setFragment(this);
        mPresenter = new HistoryPresenter(this);
        return mBinding.getRoot();
    }

    @Override
    public void getDataFromActivity() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        mViewpagerType = (ViewpagerType) bundle.getSerializable(BUNDLE_VIEW_PAGE_TYPE);
        mPollInfo.set((DataInfoItem) bundle.getParcelable(BUNDLE_HISTORY));
        mToken = bundle.getString(BUNDLE_TOKEN);
    }

    @Override
    public void start() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.INITIATE));
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.PARTICIPATE));
        fragments.add(PollHistoryFragment.getInstance(PollHistoryType.CLOSE));
        String[] titles = getActivity().getResources().getStringArray(R.array.array_history);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
    }

    public ViewPagerAdapter getAdapter() {
        return mAdapter;
    }

    public void clearData() {
        int size = mAdapter.getCount();
        for (int i = 0; i < size; i++) {
            Fragment fragment = mAdapter.getItem(i);
            if (fragment != null && fragment instanceof PollHistoryFragment) {
                ((PollHistoryFragment) fragment).clearData();
            }
        }
    }

    public void setTitle(PollHistoryType type) {
        switch (type) {
            case INITIATE:
                getActivity().setTitle(R.string.title_poll_initiate);
                break;
            case PARTICIPATE:
                getActivity().setTitle(R.string.title_poll_participate);
                break;
            case CLOSE:
                getActivity().setTitle(R.string.title_poll_close);
                break;
            default:
                break;
        }
    }
}
