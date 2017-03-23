package com.framgia.fpoll.data.source.remote.settings;

import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by tuanbg on 3/23/17.
 */
public interface SettingDataSource {
    void changeLanguage(String lang, DataCallback callback);
}
