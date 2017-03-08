package com.framgia.fpoll.data.source.remote.pollmanagerinfo;

import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by tuanbg on 3/6/17.
 */
public interface PollInfoDataSource {
    void loadData(String token, DataCallback callback);

}
