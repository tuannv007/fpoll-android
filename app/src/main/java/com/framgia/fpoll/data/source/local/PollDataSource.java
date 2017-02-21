package com.framgia.fpoll.data.source.local;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;

public interface PollDataSource extends DataSource<PollHistoryItem> {
    void getPollHistory(PollHistoryType type, GetCallback<PollHistoryItem> callback);
}
