package com.framgia.fpoll.ui.profile.language;

import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.settings.SettingRepository;
import com.framgia.fpoll.networking.ResponseItem;

/**
 * Created by tuanbg on 5/10/17.
 */

public class LanguagePresenter implements LanguageContract.Presenter {
    private SettingRepository mSettingRepository;
    private LanguageContract.ViewModel mViewModel;

    public LanguagePresenter(SettingRepository settingRepository, LanguageContract.ViewModel view) {
        mSettingRepository = settingRepository;
        mViewModel = view;
    }

    @Override
    public void changeLanguage(String lang) {
        mSettingRepository.changeLanguage(lang, new DataCallback<ResponseItem>() {
            @Override
            public void onSuccess(ResponseItem data) {
                mViewModel.changeLangStatus(data.getData().toString());
            }

            @Override
            public void onError(String msg) {
                mViewModel.changeLangStatus(msg);
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
