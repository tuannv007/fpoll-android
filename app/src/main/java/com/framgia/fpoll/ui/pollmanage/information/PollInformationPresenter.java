package com.framgia.fpoll.ui.pollmanage.information;

import android.databinding.ObservableField;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_MULTI;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_SINGER;
import static com.framgia.fpoll.util.Constant.TypeEditPoll.TYPE_EDIT_POLL;

/**
 * Created by Nhahv0902 on 2/24/2017.
 * <></>
 */
public class PollInformationPresenter implements PollInformationContract.Presenter {
    private final PollInformationContract.View mView;
    private PollRepository mRepository;
    private ObservableField<DataInfoItem> mPoll = new ObservableField<>();

    public PollInformationPresenter(PollInformationContract.View view, DataInfoItem item,
                                    PollRepository repository) {
        mView = view;
        mRepository = repository;
        mView.start();
        mPoll.set(item);
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
        String username = mPoll.get().getPoll().getName();
        String email = mPoll.get().getPoll().getEmail();
        String title = mPoll.get().getPoll().getTitle();
        int type = mPoll.get().getPoll().isMultiple() ? TYPE_MULTI : TYPE_SINGER;
        int typeEdit = TYPE_EDIT_POLL;
        String dateClose = mPoll.get().getPoll().getDateClose();
        String description = mPoll.get().getPoll().getDescription();
        UpdateInfoPollService.PollInfoBody body = new UpdateInfoPollService.PollInfoBody
            (username, email, title, type, typeEdit, dateClose, description);
        mRepository.editPollInformation(id, body, new DataCallback<ResponseItem<DataInfoItem>>() {
            @Override
            public void onSuccess(ResponseItem<DataInfoItem> data) {
                mView.saveSuccess(ActivityUtil.byString(data.getMessage()));
                mPoll.set(data.getData());
            }

            @Override
            public void onError(String msg) {
                mView.onError(msg);
            }
        });
    }
}
