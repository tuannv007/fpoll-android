package com.framgia.fpoll.ui.joinpoll;

import android.databinding.ObservableField;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;

import static com.framgia.fpoll.util.Constant.WebUrl.OPTION_DATE;
import static com.framgia.fpoll.util.Constant.WebUrl.OPTION_SIZE;
import static com.framgia.fpoll.util.Constant.WebUrl.OPTION_TITLE;

/**
 * Created by tuanbg on 4/3/17.
 */
public class JoinPollPresenter implements JoinPollContract.Presenter {

    private JoinPollContract.View mView;
    private ObservableField<String> mPollLink = new ObservableField<>();
    private ManagerRepository mRepository;

    public JoinPollPresenter(JoinPollContract.View view, ManagerRepository repository) {
        mView = view;
        mRepository = repository;
        mView.start();
    }

    public void joinPoll() {
        if (mRepository == null || mView == null || mPollLink.get() == null) return;
        mView.showProgress();
        mRepository.getPollDetail(mPollLink.get(), new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mView.hideProgress();
                if (data.isAdminToken()) {
                    mView.startUIManager(getTokenAdminFromPoll(data));
                } else {
                    mView.startUIVote(getTokenUserFromPoll(data));
                }
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideProgress();
            }
        });
    }

    private String getTokenAdminFromPoll(DataInfoItem data) {
        if (data.getPoll().getLink() != null && data.getPoll().getLink().size() == OPTION_SIZE) {
            return data.getPoll().getLink().get(OPTION_DATE).getToken();
        }
        return null;
    }

    private String getTokenUserFromPoll(DataInfoItem data) {
        if (data.getPoll().getLink() != null && data.getPoll().getLink().size() > OPTION_TITLE) {
            return data.getPoll().getLink().get(OPTION_TITLE).getToken();
        }
        return null;
    }

    public ObservableField<String> getPollLink() {
        return mPollLink;
    }
}
