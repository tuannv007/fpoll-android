package com.framgia.fpoll.data.source.local.pollhistory;

import java.util.List;

public interface DataCallBack<T> {
        void onLoaded(List<T> data);
        void onNotAvailable();
    }
