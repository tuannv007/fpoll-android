package com.framgia.fpoll.data.source.local;

import java.util.List;

/**
 * Created by tuanbg on 2/23/17.
 */
public interface IntroduceDataSource {
    interface GetCallback<T> {
        void onLoaded(List<T> data);
        void onNotAvailable();
    }
    void getData(GetCallback callback);
}
