package com.framgia.fpoll.data.source.remote.resetpassword;

import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by tuanbg on 3/14/17.
 */
public interface ResetDataSource {
    void resetPassword(User email, DataCallback callback);
}
