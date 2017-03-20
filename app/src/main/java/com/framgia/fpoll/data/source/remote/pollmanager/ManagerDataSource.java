package com.framgia.fpoll.data.source.remote.pollmanager;

import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;

import java.util.List;

/**
 * Created by Nhahv0902 on 3/7/2017.
 * <></>
 */
public interface ManagerDataSource {
    void switchPollStatus(String id, @NonNull DataCallback<String> callback);
    void deleteVoting(@NonNull String token, @NonNull DataCallback<String> callback);
    void getHistory(@NonNull String token, @NonNull final DataCallback<List<HistoryPoll>> callback);
    void getPollClosed(@NonNull String token, @NonNull DataCallback<List<HistoryPoll>> callback);
    void getPollParticipated(@NonNull String token,
                             @NonNull DataCallback<List<HistoryPoll>> callback);
    void updateLinkPoll(@NonNull String token,
                        @NonNull String oldUser,
                        @NonNull String oldAdmin,
                        @NonNull String newUser,
                        @NonNull String newAdmin,
                        @NonNull DataCallback<String> callback);
}
