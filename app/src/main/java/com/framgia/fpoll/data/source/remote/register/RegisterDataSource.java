package com.framgia.fpoll.data.source.remote.register;

import com.framgia.fpoll.data.model.User;

/**
 * Created by tuanbg on 2/28/17.
 */
public interface RegisterDataSource {
    interface RegisterCallBack {
        void onSuccess(User user);
        void onError(String message);
    }
    void register(User user,RegisterCallBack callback);
}
