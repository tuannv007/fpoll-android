package com.framgia.fpoll.ui.pollcreation.participant;

import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.ui.pollcreation.PollCreationActivity;
import java.util.List;

/**
 * Created by framgia on 23/02/2017.
 */
public class ParticipantPresenter implements ParticipantPollContract.Presenter {
    private static final String SLPIT_KEY = ",";
    private final String TAG = this.getClass().getSimpleName();
    private ParticipantPollContract.View mView;
    private PollRepository mCreationRepository;
    private PollItem mPoll;

    public ParticipantPresenter(ParticipantPollContract.View view,
            PollRepository creationRepository, PollItem poll) {
        mView = view;
        mCreationRepository = creationRepository;
        mPoll = poll;
        mView.start();
    }

    @Override
    public void createPoll(final PollCreationActivity.OnPollCreation onPollCreation) {
        if (mView == null || mCreationRepository == null) return;
        mView.showDialog();
        mPoll.setMembers(getMembersEmail(mView.getMembers()));
        mCreationRepository.createPoll(mPoll, new DataCallback<HistoryPoll>() {
            @Override
            public void onSuccess(HistoryPoll data) {
                if (mView != null) {
                    mPoll.setId(data.getId());
                    mPoll.setLink(data.getLink());
                    mView.hideDialog();
                    if (onPollCreation != null) {
                        onPollCreation.onPollCreationSuccess(data);
                    }
                }
            }

            @Override
            public void onError(String msg) {
                if (mView != null) {
                    mView.hideDialog();
                    mView.showCreatePollError(msg);
                }
            }
        });
    }

    public PollItem getPoll() {
        return mPoll;
    }

    public String getMembersEmail(List<String> memberEmails) {
        if (memberEmails == null) return null;
        StringBuilder builder = new StringBuilder();
        for (String email : memberEmails) {
            builder.append(email).append(SLPIT_KEY);
        }
        String result = builder.toString();
        if (result.endsWith(SLPIT_KEY)) {
            result = result.substring(0, builder.length() - 1);
        }
        return result;
    }
}
