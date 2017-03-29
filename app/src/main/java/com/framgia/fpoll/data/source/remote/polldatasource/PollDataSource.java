package com.framgia.fpoll.data.source.remote.polldatasource;

import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;

/**
 * Created by tuanbg on 3/21/17.
 */
public interface PollDataSource {
    void editPollInformation(int pollId, UpdateInfoPollService.PollInfoBody body,
                             DataCallback callback);
    void createPoll(PollItem pollItem, @NonNull DataCallback<PollItem> callback);
    void editPoll(int typeEdit, PollItem pollItem, @NonNull final DataCallback<DataInfoItem>
        callback);
}
