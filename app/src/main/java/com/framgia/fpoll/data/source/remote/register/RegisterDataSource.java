package com.framgia.fpoll.data.source.remote.register;

import com.framgia.fpoll.data.model.authorization.User;

/**
 * Created by tuanbg on 2/28/17.
 */
public interface RegisterDataSource {
    void register(User user, RegisterCallBack callback);

    interface RegisterCallBack {
        void onSuccess(User user);

        void onError(String message);
    }
}
