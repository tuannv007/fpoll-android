package com.framgia.fpoll.ui.pollmanage.action;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.util.SharePreferenceUtil;

import static com.framgia.fpoll.util.Constant.DataConstant.DATA_PREFIX_TOKEN;

/**
 * Created by tran.trung.phong on 01/03/2017.
 */
public class EditPollPresenter implements EditPollContract.Presenter {
    private static final String KEY_SPLIT = ";";
    private static final int NUMBER_ADMIN = 0;
    private static final int NUMBER_USER = 1;
    private static final int OPTION_TITLE = 0;
    private static final int OPTION_DATE = 1;
    private EditPollContract.View mView;
    private ObservableField<String> mLinkManager = new ObservableField<>();
    private ObservableField<String> mLinkVoting = new ObservableField<>();
    private ObservableBoolean mPollOpen = new ObservableBoolean();
    private String mOldLinkUser;
    private String mOldLinkAdmin;
    private ManagerRepository mRepository;
    private String mToken;
    private User mUser;

    public EditPollPresenter(EditPollContract.View view, ManagerRepository repository,
                             SharePreferenceUtil preference, String token) {
        mView = view;
        mRepository = repository;
        mToken = token;
        mUser = preference.getUser();
        mView.start();
        loadData();
    }

    @Override
    public void loadData() {
        if (mRepository == null || mView == null) return;
        mView.showProgressDialog();
        mRepository.getPoll(mToken, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mPollOpen.set(data.getPoll().isOpen());
                mOldLinkAdmin = data.getPoll().getLink().get(NUMBER_ADMIN).getToken();
                mOldLinkUser = data.getPoll().getLink().get(NUMBER_USER).getToken();
                mLinkVoting.set(mOldLinkUser);
                mLinkManager.set(mOldLinkAdmin);
                mView.hideProgressDialog();
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideProgressDialog();
            }
        });
    }

    @Override
    public void updateLinkPoll() {
        if (mRepository == null || mView == null) return;
        new UpdateTokenValidation(mLinkVoting.get(), mLinkManager.get()).validate(
            new UpdateTokenValidation.UpdateTokenCallback() {
                @Override
                public void onSuccess() {
                    mView.showProgressDialog();
                    submitUpdateLink();
                }

                @Override
                public void onError(UpdateTokenValidation.UpdateTokenError error) {
                    switch (error) {
                        case LINK_USER:
                            mView.showMessage(R.string.msg_link_user_empty);
                            break;
                        case LINK_ADMIN:
                            mView.showMessage(R.string.msg_link_admin_empty);
                            break;
                        default:
                            break;
                    }
                }
            });
    }

    private void submitUpdateLink() {
        mRepository.updateLinkPoll(DATA_PREFIX_TOKEN + mUser.getToken(), mOldLinkUser,
            mOldLinkAdmin, mLinkVoting.get(), mLinkManager.get(), new DataCallback<String>() {
                @Override
                public void onSuccess(String data) {
                    mView.showMessage(data);
                    mView.hideProgressDialog();
                    loadData();
                }

                @Override
                public void onError(String msg) {
                    mView.hideProgressDialog();
                    mView.showMessage(msg);
                }
            });
    }

    @Override
    public void viewHistory() {
        mView.viewHistory();
    }

    @Override
    public void editPoll() {
        if (mView == null || mRepository == null) return;
        mRepository.getPoll(mToken, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mView.startModifyPoll(data.getPoll());
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void closePoll() {
        if (mRepository == null) return;
        mRepository.switchPollStatus(mToken, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void deleteVoting() {
        if (mRepository == null) return;
        mRepository.deleteVoting(mToken, new DataCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.showMessage(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void createDuplicate() {
        if (mRepository == null) return;
        mRepository.getPoll(mToken, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                handlerOption(data.getPoll());
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    private void handlerOption(PollItem poll) {
        if (poll == null || poll.getOptions() == null) return;
        for (Option option : poll.getOptions()) {
            String[] title = option.getName().split(KEY_SPLIT);
            if (title[OPTION_TITLE] != null) option.setName(title[0]);
            if (title[OPTION_DATE] != null) option.setDate(title[1]);
        }
        mView.startUiPollCreation(poll);
    }

    public ObservableBoolean getPollOpen() {
        return mPollOpen;
    }

    public ObservableField<String> getLinkManager() {
        return mLinkManager;
    }

    public ObservableField<String> getLinkVoting() {
        return mLinkVoting;
    }
}
