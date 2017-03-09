package com.framgia.fpoll.data.source.remote.creation;

import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;

/**
 * Created by framgia on 06/03/2017.
 */
public interface CreationDataSource {
    void createPoll(PollItem pollItem, @NonNull DataCallback<PollItem> callback);
}
