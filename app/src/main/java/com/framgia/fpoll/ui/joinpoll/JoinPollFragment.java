package com.framgia.fpoll.ui.joinpoll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.databinding.FragmentJoinPollBinding;
import com.framgia.fpoll.ui.mainstart.NewMainActivity;
import com.framgia.fpoll.ui.pollmanage.ManagePollActivity;
import com.framgia.fpoll.ui.votemanager.LinkVoteActivity;
import com.framgia.fpoll.util.ActivityUtil;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by tuanbg on 4/3/17.
 */
public class JoinPollFragment extends Fragment implements JoinPollContract.View {
    public static JoinPollFragment newInstance() {
        return new JoinPollFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        FragmentJoinPollBinding binding =
                FragmentJoinPollBinding.inflate(inflater, container, false);
        binding.setPresenter(
                new JoinPollPresenter(this, ManagerRepository.getInstance(getActivity())));
        binding.setView(this);
        return binding.getRoot();
    }

    @Override
    public void start() {

    }

    @Override
    public void startUIManager(String poll) {
        startActivity(ManagePollActivity.getTokenIntent(getApplicationContext(), poll));
    }

    @Override
    public void startUIVote(String poll) {
        startActivity(LinkVoteActivity.getTokenIntent(getApplicationContext(), poll));
    }

    @Override
    public void showProgress() {
        ((NewMainActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void hideProgress() {
        ((NewMainActivity) getActivity()).hideProgressDialog();
    }

    @Override
    public void showMessage(String msg) {
        ActivityUtil.showToast(getApplicationContext(), msg);
    }
}
