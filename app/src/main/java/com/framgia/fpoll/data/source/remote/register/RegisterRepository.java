package com.framgia.fpoll.data.source.remote.register;

import android.content.Context;
import com.framgia.fpoll.data.model.authorization.User;

/**
 * Created by tuanbg on 2/28/17.
 */
public class RegisterRepository implements RegisterDataSource {
    private static RegisterRepository sRegisterRepository;
    private static RegisterDataSource mRemoteDataSource;

    public RegisterRepository(RegisterRemoteDataSource registerRemoteDataSource) {
        mRemoteDataSource = registerRemoteDataSource;
    }

    public static RegisterRepository getInstance(Context context) {
        if (sRegisterRepository == null) {
            sRegisterRepository =
                    new RegisterRepository(RegisterRemoteDataSource.getInstance(context));
        }
        return sRegisterRepository;
    }

    @Override
    public void register(User user, final RegisterCallBack callback) {
        mRemoteDataSource.register(user, new RegisterCallBack() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
    }
}
