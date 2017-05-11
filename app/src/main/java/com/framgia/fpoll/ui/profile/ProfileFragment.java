package com.framgia.fpoll.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.fpoll.data.source.remote.login.LoginRepository;
import com.framgia.fpoll.databinding.FragmentProfileBinding;
import com.framgia.fpoll.ui.mainstart.NewMainActivity;
import com.framgia.fpoll.util.SharePreferenceUtil;

public class ProfileFragment extends Fragment {
    private ProfileContract.ViewModel mViewModel;
    private FragmentProfileBinding mBinding;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        mViewModel = new ProfileViewModel((NewMainActivity) getActivity(),
                SharePreferenceUtil.getIntances(getActivity()));
        ProfileContract.Presenter presenter =
                new ProfilePresenter(mViewModel, SharePreferenceUtil.getIntances(getActivity()),
                        LoginRepository.getInstance(getActivity()));
        mViewModel.setPresenter(presenter);
        mBinding.setViewModel((ProfileViewModel) mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
