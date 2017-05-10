package com.framgia.fpoll.ui.profile.language;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.framgia.fpoll.data.source.remote.settings.SettingRepository;
import com.framgia.fpoll.databinding.DialogFragmentLanguageBinding;
import com.framgia.fpoll.ui.mainstart.NewMainActivity;
import com.framgia.fpoll.util.SharePreferenceUtil;

/**
 * Created by tuanbg on 5/10/17.
 */

public class LanguageDialogFragment extends DialogFragment {
    private DialogFragmentLanguageBinding mBinding;
    private LanguageViewModel mViewModel;

    public static LanguageDialogFragment newInstance() {
        Bundle args = new Bundle();
        LanguageDialogFragment fragment = new LanguageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mBinding = DialogFragmentLanguageBinding.inflate(inflater, container, false);
        mViewModel = new LanguageViewModel(this, (NewMainActivity) getActivity(),
                SharePreferenceUtil.getIntances(getActivity()));
        LanguageContract.Presenter presenter =
                new LanguagePresenter(SettingRepository.getInstance(getActivity()), mViewModel);
        mViewModel.setPresenter(presenter);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }
}
