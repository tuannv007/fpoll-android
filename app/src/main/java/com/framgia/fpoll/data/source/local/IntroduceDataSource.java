package com.framgia.fpoll.data.source.local;

import java.util.List;

/**
 * Created by tuanbg on 2/23/17.
 */
public interface IntroduceDataSource {
    void getData(GetCallback callback);

    interface GetCallback<T> {
        void onLoaded(List<T> data);

        void onNotAvailable();
    }
}
