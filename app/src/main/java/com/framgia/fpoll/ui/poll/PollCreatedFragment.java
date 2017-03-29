package com.framgia.fpoll.ui.poll;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollCreatedItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.remote.resentemail.ResentEmailRepository;
import com.framgia.fpoll.databinding.FragmentCreatedPollBinding;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.ui.history.ViewpagerType;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.util.ActivityUtil;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.framgia.fpoll.util.Constant.BundleConstant.BUNDLE_POLL_ITEM;
import static com.framgia.fpoll.util.Constant.ConstantApi.BASE_URL;
import static com.framgia.fpoll.util.Constant.POSITION_LINK_ADMIN;
import static com.framgia.fpoll.util.Constant.POSITION_LINK_INVITE;
import static com.framgia.fpoll.util.Constant.TITLE_TYPE_TEXT;

/**
 * Created by tuanbg on 2/21/17.
 */
public class PollCreatedFragment extends Fragment implements PollCreatedContract.View {
    private FragmentCreatedPollBinding mBinding;
    private PollCreatedItem mItem = new PollCreatedItem();
    private PollCreatedPresenter mPresenter;
    private ClipboardManager mClipboardManager;
    private String mlLinkAdmin;
    private String mLink;

    public static PollCreatedFragment newInstance(PollItem pollItem) {
        PollCreatedFragment pollCreatedFragment = new PollCreatedFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_POLL_ITEM, pollItem);
        pollCreatedFragment.setArguments(bundle);
        return pollCreatedFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentCreatedPollBinding.inflate(inflater, container, false);
        mBinding.setFragment(this);
        start();
        mBinding.setHandler(new PollCreatedHandler(mPresenter));
        return mBinding.getRoot();
    }

    @Override
    public void start() {
        mPresenter =
            new PollCreatedPresenter(this, ResentEmailRepository.getInstance(getActivity()));
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        getData();
    }

    public void getData() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        PollItem item = bundle.getParcelable(BUNDLE_POLL_ITEM);
        if (item == null) return;
        mlLinkAdmin = item.getLink().get(POSITION_LINK_ADMIN).getToken();
        mLink = item.getLink().get(POSITION_LINK_INVITE).getToken();
        mItem.setUsername(item.getName());
        mItem.setEmail(item.getEmail());
        mItem.setLink(BASE_URL + mLink);
        mItem.setLinkAdmin(BASE_URL + mlLinkAdmin);
        mItem.setIdPoll(item.getId());
    }

    @Override
    public void copyLinkInvite() {
        if (mItem.getLink() == null) return;
        copy(mItem.getLink());
    }

    public void copy(String link) {
        ClipData clipData;
        clipData = ClipData.newPlainText(TITLE_TYPE_TEXT, link);
        mClipboardManager.setPrimaryClip(clipData);
        ActivityUtil.showToast(getActivity(), R.string.msg_copy_success);
    }

    @Override
    public void startUiPollManager() {
        startActivity(ManagePollActivity.getManageIntent(getActivity(), ViewpagerType.MANAGE,
            mlLinkAdmin));
    }

    @Override
    public void copyLinkManager() {
        if (mItem.getLinkAdmin() == null) return;
        copy(mItem.getLinkAdmin());
    }

    @Override
    public void resentSuccess(ResponseItem data) {
        ActivityUtil.showToast(getActivity(), data.getMessage().toString());
    }

    @Override
    public void resentError(String msg) {
        ActivityUtil.showToast(getActivity(), msg);
    }

    @Override
    public void startUiLinkInviteVote() {
        startActivity(LinkVoteActivity.getTokenIntent(getActivity(), mLink));
    }

    public PollCreatedItem getItem() {
        return mItem;
    }
}
