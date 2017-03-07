package com.framgia.fpoll.data.source.remote.creation;

import com.framgia.fpoll.data.ApiRestClient.APIService.pollcreationservice.PollItem;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by framgia on 06/03/2017.
 */
public interface CreationDataSource {
    void createPoll(PollItem pollItem, DataCallback callback);
}
