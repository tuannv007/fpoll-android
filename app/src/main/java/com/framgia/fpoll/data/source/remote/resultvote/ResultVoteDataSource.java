package com.framgia.fpoll.data.source.remote.resultvote;

import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by tuanbg on 3/12/17.
 */
public interface ResultVoteDataSource {
    void loadData(String token, DataCallback callback);
}
