package com.framgia.fpoll.ui.pollcreated;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollCreatedItem;
import com.framgia.fpoll.databinding.FragmentCreatedPollBinding;
import com.framgia.fpoll.util.ActivityUtil;
import com.framgia.fpoll.util.Constant;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedFragment extends Fragment implements PollCreatedContract.View {
    private FragmentCreatedPollBinding mBinding;
    private PollCreatedItem mItem = new PollCreatedItem();
    private PollCreatedPresenter mPresenter;
    private ClipboardManager mClipboardManager;

    public static PollCreatedFragment getInstance() {
        return new PollCreatedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_created_poll, container, false);
        start();
        mBinding.setHandler(new PollCreatedHandlerAction(mPresenter));
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mPresenter = new PollCreatedPresenter(this);
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
    }

    @Override
    public void copyUrl() {
        ClipData clipData;
        if (mItem.getLink() == null) return;
        clipData = ClipData.newPlainText(Constant.TITLE_TYPE_TEXT, mItem.getLink());
        mClipboardManager.setPrimaryClip(clipData);
        ActivityUtil.showToast(getActivity(), R.string.msg_copy_success);
    }

    public PollCreatedItem getItem() {
        return mItem;
    }
}
