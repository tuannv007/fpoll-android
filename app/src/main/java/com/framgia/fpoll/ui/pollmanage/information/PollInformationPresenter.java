package com.framgia.fpoll.ui.pollmanage.information;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.view.View;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.authorization.User;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.data.source.remote.pollmanager.ManagerRepository;
import com.framgia.fpoll.networking.api.UpdatePollService;
import com.framgia.fpoll.util.SharePreferenceUtil;

import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_MULTI;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_SINGER;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class PollInformationPresenter implements PollInformationContract.Presenter {
    private final PollInformationContract.View mView;
    private final String mToken;
    private final SharePreferenceUtil mPresenter;
    private PollRepository mRepository;
    private ManagerRepository mManagerRepository;
    private ObservableField<Poll> mPoll = new ObservableField<>();
    private ObservableInt mUserNameVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mEmailVisibility = new ObservableInt(View.VISIBLE);

    public PollInformationPresenter(PollInformationContract.View view, PollRepository repository,
            ManagerRepository manageRepository, SharePreferenceUtil preference, DataInfoItem poll,
            String token) {
        mView = view;
        mRepository = repository;
        mToken = token;
        mManagerRepository = manageRepository;
        mPresenter = preference;
        if (poll != null && poll.getPoll() != null) mPoll.set(poll.getPoll());
        if (poll == null && token != null) loadData();
        initData(poll);
        mView.start();
    }

    private void initData(DataInfoItem poll) {
        User user = mPresenter.getUser();
        if (poll == null || poll.getPoll() == null || poll.getPoll().getUser() == null) return;
        User userPoll = poll.getPoll().getUser();
        if (user == null || userPoll == null) return;
        mUserNameVisibility.set((mPresenter.isLogin()
                && !TextUtils.isEmpty(userPoll.getUsername())
                && TextUtils.equals(user.getUsername(), userPoll.getUsername())) ? View.GONE
                : View.VISIBLE);
        mEmailVisibility.set((mPresenter.isLogin()
                && !TextUtils.isEmpty(userPoll.getEmail())
                && TextUtils.equals(user.getEmail(), userPoll.getEmail())) ? View.GONE
                : View.VISIBLE);
    }

    @Override
    public void loadData() {
        if (mManagerRepository == null || mView == null || mToken == null) return;
        mManagerRepository.getPollDetail(mToken, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mPoll.set(data.getPoll());
                mView.onGetPollSuccessful(data);
                initData(data);
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

    public void saveInformation() {
        if (mPoll.get() == null || mRepository == null) return;
        String username = mPoll.get().getUser().getUsername();
        String email = mPoll.get().getUser().getEmail();
        String title = mPoll.get().getTitle();
        int type = mPoll.get().isMultiple() ? TYPE_MULTI : TYPE_SINGER;
        String dateClose = mPoll.get().getDateClose();
        String description = mPoll.get().getDescription();
        UpdatePollService.PollInfoBody body =
                new UpdatePollService.PollInfoBody(username, email, title, type, dateClose,
                        description, mPoll.get().getLocation());
        mView.showProgress();
        mRepository.updateInformation(mPoll.get().getId(), body, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mView.showMessage(R.string.update_success);
                mPoll.set(data.getPoll());
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

    public ObservableField<Poll> getPoll() {
        return mPoll;
    }

    public ObservableInt getUserNameVisibility() {
        return mUserNameVisibility;
    }

    public ObservableInt getEmailVisibility() {
        return mEmailVisibility;
    }
}
