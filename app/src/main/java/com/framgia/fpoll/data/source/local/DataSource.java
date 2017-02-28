package com.framgia.fpoll.data.source.local;

import java.util.List;

public interface DataSource<T> {
    interface GetCallback<T> {
        void onLoaded(List<T> data);
        void onNotAvailable();
    }
}
