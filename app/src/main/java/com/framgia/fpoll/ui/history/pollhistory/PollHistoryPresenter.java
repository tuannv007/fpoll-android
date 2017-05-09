package com.framgia.fpoll.ui.history.pollhistory;

import android.databinding.ObservableField;
import android.view.View;
import com.framgia.fpoll.data.model.EmptyModel;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.model.poll.HistoryPoll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.ui.history.PollHistoryType;
import com.framgia.fpoll.util.SharePreferenceUtil;
import java.util.List;

import static com.framgia.fpoll.data.model.EmptyModel.State.NO_INTERNET;
import static com.framgia.fpoll.data.model.EmptyModel.State.NO_LOGIN;
import static com.framgia.fpoll.data.model.EmptyModel.State.NO_POLL;
import static com.framgia.fpoll.data.model.EmptyModel.State.NO_POLL_CLOSE;
import static com.framgia.fpoll.data.model.EmptyModel.State.NO_POLL_PARTICIPATE;
import static com.framgia.fpoll.util.Constant.DataConstant.DATA_PREFIX_TOKEN;

/**
 * Created by Nhahv0902 on 2/14/2017.
 * <></>
 */
public class PollHistoryPresenter implements PollHistoryContract.Presenter {
    private static final int NUMBER_LINK_ADMIN = 1;
    private PollHistoryContract.View mView;
    private PollHistoryType mHistoryType;
    private ManagerRepository mRepository;
    private User mUser;
    private SharePreferenceUtil mPreference;
    private ObservableField<EmptyModel> mEmptyModel = new ObservableField<>();

    public PollHistoryPresenter(PollHistoryContract.View view, PollHistoryType typeHistory,
            ManagerRepository repository, SharePreferenceUtil preference) {
        mView = view;
        mRepository = repository;
        mHistoryType = typeHistory;
        mUser = preference.getUser();
        mPreference = preference;
        mEmptyModel.set(new EmptyModel(View.GONE));
        mView.start();
    }

    @Override
    public void getData() {
        if (mView == null || mRepository == null) return;
        if (!mPreference.isLogin()) {
            mView.setLoadingFalse();
            mEmptyModel.set(
                    new EmptyModel(NO_LOGIN, View.VISIBLE, new EmptyModel.OnActionClickListenner() {
                        @Override
                        public void onActionClick() {
                            mView.onOpenLoginClick();
                        }
                    }));
            return;
        }
        mView.setLoadingTrue();
        switch (mHistoryType) {
            case INITIATE:
                mRepository.getHistory(DATA_PREFIX_TOKEN + mUser.getToken(),
                        new DataCallback<List<HistoryPoll>>() {
                            @Override
                            public void onSuccess(List<HistoryPoll> data) {
                                loadDataSuccess(data);
                                if (data.size() == 0) {
                                    mEmptyModel.set(new EmptyModel(NO_POLL, View.VISIBLE, null));
                                } else {
                                    mEmptyModel.set(new EmptyModel(View.GONE));
                                }
                            }

                            @Override
                            public void onError(String msg) {
                                loadDataError(msg);
                                mEmptyModel.set(new EmptyModel(NO_INTERNET, View.VISIBLE, null));
                            }
                        });
                break;
            case PARTICIPATE:
                mRepository.getPollParticipated(DATA_PREFIX_TOKEN + mUser.getToken(),
                        new DataCallback<List<HistoryPoll>>() {
                            @Override
                            public void onSuccess(List<HistoryPoll> data) {
                                loadDataSuccess(data);
                                if (data.size() == 0) {
                                    mEmptyModel.set(
                                            new EmptyModel(NO_POLL_PARTICIPATE, View.VISIBLE,
                                                    null));
                                } else {
                                    mEmptyModel.set(new EmptyModel(View.GONE));
                                }
                            }

                            @Override
                            public void onError(String msg) {
                                loadDataError(msg);
                                mEmptyModel.set(new EmptyModel(NO_INTERNET, View.VISIBLE, null));
                            }
                        });
                break;
            case CLOSE:
                mRepository.getPollClosed(DATA_PREFIX_TOKEN + mUser.getToken(),
                        new DataCallback<List<HistoryPoll>>() {
                            @Override
                            public void onSuccess(List<HistoryPoll> data) {
                                loadDataSuccess(data);
                                if (data.size() == 0) {
                                    mEmptyModel.set(
                                            new EmptyModel(NO_POLL_CLOSE, View.VISIBLE, null));
                                } else {
                                    mEmptyModel.set(new EmptyModel(View.GONE));
                                }
                            }

                            @Override
                            public void onError(String msg) {
                                loadDataError(msg);
                                mEmptyModel.set(new EmptyModel(NO_INTERNET, View.VISIBLE, null));
                            }
                        });
                break;
            default:
                break;
        }
    }

    private void loadDataError(String msg) {
        mView.showMessage(msg);
        mView.setLoadingFalse();
    }

    private void loadDataSuccess(List<HistoryPoll> data) {
        mView.setLoadingFalse();
        mView.setPollHistory(data);
    }

    @Override
    public void openPollHistory(HistoryPoll data) {
        switch (mHistoryType) {
            case INITIATE:
                if (data == null
                        || data.getLink().get(NUMBER_LINK_ADMIN) == null
                        || data.getLink().get(NUMBER_LINK_ADMIN).getToken() == null) {
                    return;
                }
                if (data.isOpen()) {
                    mView.onOpenManagerPollClick(data.getLink().get(NUMBER_LINK_ADMIN).getToken());
                }
                break;
            case PARTICIPATE:
                if (data == null
                        || data.getLink().get(NUMBER_LINK_ADMIN) == null
                        || data.getLink().get(NUMBER_LINK_ADMIN).getToken() == null) {
                    return;
                }
                if (data.isOpen()) {
                    mView.onOpenVoteClick(data.getLink().get(NUMBER_LINK_ADMIN).getToken());
                } else {
                    mView.showPollClosedDialog();
                }
                break;
            case CLOSE:
                mView.showConfirmDialog(data);
                break;
            default:
                break;
        }
    }

    @Override
    public void reopenPoll(HistoryPoll data) {
        mView.showDialog();
        if (mRepository == null) return;
        mRepository.switchPollStatus(String.valueOf(data.getId()), new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
                getData();
                mView.hideDialog();
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideDialog();
            }
        });
    }

    @Override
    public void onHideBottomNavigation() {
        if (mView != null) mView.hideBottomNavigation();
    }

    @Override
    public void onShowBottomNavigation() {
        if (mView != null) mView.showBottomNavigation();
    }

    public ObservableField<EmptyModel> getEmptyModel() {
        return mEmptyModel;
    }
}
