package com.framgia.fpoll.data.source.local.pollhistory;

import com.framgia.fpoll.data.enums.PollHistoryType;
import com.framgia.fpoll.data.model.PollHistoryItem;
import com.framgia.fpoll.data.source.local.PollDataSource;

import java.util.ArrayList;
import java.util.List;

public class PollHistoryLocalDataSource implements PollDataSource {
    private static PollHistoryLocalDataSource sLocalDataSource;

    private PollHistoryLocalDataSource() {
    }

    public static PollHistoryLocalDataSource getInstance() {
        if (sLocalDataSource == null) {
            sLocalDataSource = new PollHistoryLocalDataSource();
        }
        return sLocalDataSource;
    }

    @Override
    public void getPollHistory(PollHistoryType type, GetCallback<PollHistoryItem> callback) {
        if (callback == null) return;
        List<PollHistoryItem> items = new ArrayList<>();
        switch (type) {
            case PARTICIPATE:
                items.add(new PollHistoryItem("Chon ai dep nhat", 12, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai xau nhat", 12, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai dep nhat", 13, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai gioi nhat", 20, "2 Week", "admin"));
                break;
            case CLOSE:
                items.add(new PollHistoryItem("Chon ai dep nhat", 12, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai xau nhat", 12, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai dep nhat", 13, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai gioi nhat", 20, "2 Week", "admin"));
                break;
            case INITIATE:
            default:
                items.add(new PollHistoryItem("Chon ai dep nhat", 12, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai xau nhat", 12, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai dep nhat", 13, "2 Week", "admin"));
                items.add(new PollHistoryItem("Chon ai gioi nhat", 20, "2 Week", "admin"));
                break;
        }
        callback.onLoaded(items);
    }
}
