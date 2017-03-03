package com.framgia.fpoll.data.source.local;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;

import java.util.List;

public interface PollDataSource {
    interface PollCallBack<T> {
        void onLoaded(List<T> data);
        void onNotAvailable();
    }
    void getPollHistory(PollHistoryType type, PollCallBack<PollHistoryItem> callback);
}
