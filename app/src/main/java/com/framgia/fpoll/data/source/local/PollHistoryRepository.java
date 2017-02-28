package com.framgia.fpoll.data.source.local;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;
import com.framgia.fpoll.data.source.local.pollhistory.DataCallBack;
import com.framgia.fpoll.data.source.local.pollhistory.PollHistoryLocalDataSource;

import java.util.List;

/**
 * Created by nhahv on 2/23/17.
 * <></>
 */
public class PollHistoryRepository implements PollDataSource {
    private static PollHistoryRepository sRepository;
    private PollDataSource mLocalDataSource;

    public static PollHistoryRepository getInstance() {
        if (sRepository == null) {
            sRepository = new PollHistoryRepository(PollHistoryLocalDataSource.getInstance());
        }
        return sRepository;
    }

    private PollHistoryRepository(PollDataSource dataSource) {
        mLocalDataSource = dataSource;
    }

    @Override
    public void getPollHistory(PollHistoryType type, final DataCallBack<PollHistoryItem> callback) {
        if (callback == null) return;
        mLocalDataSource.getPollHistory(type, new DataCallBack<PollHistoryItem>() {
            @Override
            public void onLoaded(List<PollHistoryItem> data) {
                callback.onLoaded(data);
            }

            @Override
            public void onNotAvailable() {
                callback.onNotAvailable();
            }
        });
    }
}
