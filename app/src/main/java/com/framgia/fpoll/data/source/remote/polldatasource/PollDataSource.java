package com.framgia.fpoll.data.source.remote.polldatasource;

import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;

/**
 * Created by tuanbg on 3/21/17.
 */
public interface PollDataSource {
    void editPollInformation(int pollId, UpdateInfoPollService.PollInfoBody body,
            @NonNull DataCallback<DataInfoItem> callback);

    void createPoll(PollItem pollItem, @NonNull DataCallback<HistoryPoll> callback);

    void editPoll(int typeEdit, PollItem pollItem,
            @NonNull final DataCallback<DataInfoItem> callback);

    void getActivity(String token, @NonNull DataCallback<DataInfoItem> callback);
}
