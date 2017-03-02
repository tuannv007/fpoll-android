package com.framgia.fpoll.data.source.local;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;
import com.framgia.fpoll.data.source.local.pollhistory.DataCallBack;

public interface PollDataSource {
    void getPollHistory(PollHistoryType type,
                        DataCallBack<PollHistoryItem> callback);
}
