package com.framgia.fpoll.ui.joinpoll;

import android.databinding.ObservableField;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;

import static com.framgia.fpoll.util.Constant.TokenType.TYPE_ADMIN;
import static com.framgia.fpoll.util.Constant.TokenType.TYPE_USER;

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
                switch (data.getTokenType()) {
                    case TYPE_USER:
                        mView.startUIVote(data);
                        break;
                    case TYPE_ADMIN:
                        mView.startUIManager(data);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideProgress();
            }
        });
    }

    public ObservableField<String> getPollLink() {
        return mPollLink;
    }
}
