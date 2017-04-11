package com.framgia.fpoll.ui.pollmanage.information;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;
import com.framgia.fpoll.util.SharePreferenceUtil;

import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_MULTI;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_SINGER;
import static com.framgia.fpoll.util.Constant.TypeEditPoll.TYPE_EDIT_POLL;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class PollInformationPresenter implements PollInformationContract.Presenter {
    private final PollInformationContract.View mView;
    private final String mToken;
    private PollRepository mRepository;
    private ManagerRepository mManagerRepository;
    private ObservableField<DataInfoItem> mPoll = new ObservableField<>();
    private ObservableBoolean mIsLogin = new ObservableBoolean();

    public PollInformationPresenter(PollInformationContract.View view, PollRepository repository,
            ManagerRepository manageRepository, SharePreferenceUtil preference, String token) {
        mView = view;
        mRepository = repository;
        mToken = token;
        mManagerRepository = manageRepository;
        mIsLogin.set(preference.isLogin());
        mView.start();
        loadData();
    }

    @Override
    public void loadData() {
        if (mManagerRepository == null || mView == null || mToken == null) return;
        mManagerRepository.getPollDetail(mToken, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mView.onGetPollSuccessful(data);
            }

            @Override
            public void onError(String msg) {
                mView.onGetPollFailed(msg);
            }
        });
    }

    @Override
    public void clickLinkVote() {
        mView.startUiVoting();
    }

    @Override
    public void clickViewOption() {
        mView.showDialogOption();
    }

    @Override
    public void clickViewSetting() {
        mView.showDialogSetting();
    }

    @Override
    public void saveInformation(int id) {
        if (mPoll.get() == null || mRepository == null) return;
        String username = mPoll.get().getPoll().getUser().getUsername();
        String email = mPoll.get().getPoll().getUser().getEmail();
        String title = mPoll.get().getPoll().getTitle();
        int type = mPoll.get().getPoll().isMultiple() ? TYPE_MULTI : TYPE_SINGER;
        String dateClose = mPoll.get().getPoll().getDateClose();
        String description = mPoll.get().getPoll().getDescription();
        UpdateInfoPollService.PollInfoBody body =
                new UpdateInfoPollService.PollInfoBody(username, email, title, type, TYPE_EDIT_POLL,
                        dateClose, description);
        mView.showProgress();
        mRepository.editPollInformation(id, body, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mView.showMessage(R.string.update_success);
                mPoll.set(data);
                mView.hideProgress();
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
                mView.hideProgress();
            }
        });
    }

    @Override
    public void showDateTimePicker() {
        if (mView != null) mView.showDateTimePicker();
    }

    public ObservableBoolean getIsLogin() {
        return mIsLogin;
    }
}
