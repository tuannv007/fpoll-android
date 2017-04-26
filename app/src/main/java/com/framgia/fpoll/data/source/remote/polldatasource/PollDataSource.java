package com.framgia.fpoll.data.source.remote.polldatasource;

import android.support.annotation.NonNull;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.UpdatePollService;
import java.util.List;

/**
 * Created by tuanbg on 3/21/17.
 */
public interface PollDataSource {
    void updateInformation(int pollId, UpdatePollService.PollInfoBody body,
            @NonNull DataCallback<DataInfoItem> callback);

    void createPoll(PollItem pollItem, @NonNull DataCallback<HistoryPoll> callback);

    void updateOptionSetting(int editType, PollItem pollItem,
            @NonNull final DataCallback<DataInfoItem> callback);

    void getActivity(String token, @NonNull DataCallback<DataInfoItem> callback);

    void updateOption(int id, @NonNull List<Option> options,
            @NonNull DataCallback<DataInfoItem> callback);
}
