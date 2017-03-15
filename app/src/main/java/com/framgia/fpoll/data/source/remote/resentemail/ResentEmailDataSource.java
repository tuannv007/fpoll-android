package com.framgia.fpoll.data.source.remote.resentemail;

import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by tuanbg on 3/15/17.
 */
public interface ResentEmailDataSource {
    void resentEmail(int pollId, DataCallback callback);
}
