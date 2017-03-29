package com.framgia.fpoll.ui.polledition.editsetting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.databinding.FragmentEditSettingBinding;
import com.framgia.fpoll.widget.FPollProgressDialog;

import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;

/**
 * Created by framgia on 17/03/2017.
 */
public class EditSettingFragment extends Fragment implements EditSettingContract.View {
    private FragmentEditSettingBinding mBinding;
    private EditSettingContract.Presenter mPresenter;
    private PollItem mPollItem;
    private FPollProgressDialog mProgressDialog;

    public static EditSettingFragment newInstance(PollItem pollItem) {
        EditSettingFragment editSettingFragment = new EditSettingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        editSettingFragment.setArguments(bundle);
        return editSettingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentEditSettingBinding.inflate(inflater, container, false);
        mPollItem = getArguments().getParcelable(BUNDLE_POLL_ITEM);
        mPresenter =
            new EditSettingPresenter(this, mPollItem, PollRepository.getInstance(getActivity()));
        mBinding.setHandler(new EditSettingHandler(mPresenter));
        mBinding.setPresenter((EditSettingPresenter) mPresenter);
        mProgressDialog = new FPollProgressDialog(getActivity());
        return mBinding.getRoot();
    }

    @Override
    public void start() {
    }

    @Override
    public void back() {
    }

    @Override
    public void showDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
