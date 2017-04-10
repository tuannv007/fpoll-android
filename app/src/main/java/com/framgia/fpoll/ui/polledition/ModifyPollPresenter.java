package com.framgia.fpoll.ui.polledition;

import android.support.annotation.Nullable;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.networking.api.PollEditionApi;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_MULTI;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_SINGER;
import static com.framgia.fpoll.util.Constant.TypeEditPoll.TYPE_EDIT_POLL;

/**
 * Created by framgia on 15/03/2017.
 */
public class ModifyPollPresenter implements ModifyPollContract.Presenter {
    private final PollItem mPoll;
    private ModifyPollContract.View mView;
    private PollRepository mRepository;

    public ModifyPollPresenter(ModifyPollContract.View view, PollItem poll,
            PollRepository repository) {
        mView = view;
        mPoll = poll;
        mRepository = repository;
        mView.start();
    }

    @Override
    public void onClickSave() {
        if (mView == null || mRepository == null) return;
        mView.submitEditPoll();
    }

    @Override
    public void submitEditInformation() {
        mView.showProgress();
        String username = mPoll.getName();
        String email = mPoll.getEmail();
        String title = mPoll.getTitle();
        int type = mPoll.isMultiple() ? TYPE_MULTI : TYPE_SINGER;
        String dateClose = mPoll.getDateClose();
        String description = mPoll.getDescription();
        UpdateInfoPollService.PollInfoBody body =
                new UpdateInfoPollService.PollInfoBody(username, email, title, type, TYPE_EDIT_POLL,
                        dateClose, description);
        mRepository.editPollInformation(mPoll.getId(), body, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                mView.hideProgress();
                mView.showMessage(R.string.update_success);
            }

            @Override
            public void onError(String msg) {
                mView.hideProgress();
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void submitEditOption() {
        List<Option> listOptionReal = validateOption();
        if (listOptionReal == null) return;
        mPoll.setOptions(listOptionReal);
        mView.showProgress();
        mRepository.editPoll(PollEditionApi.TYPE_EDIT_OPTION, mPoll,
                new DataCallback<DataInfoItem>() {
                    @Override
                    public void onSuccess(DataInfoItem data) {
                        mView.hideProgress();
                        mView.showMessage(R.string.update_success);
                    }

                    @Override
                    public void onError(String msg) {
                        mView.hideProgress();
                        mView.showMessage(msg);
                    }
                });
    }

    @Nullable
    private List<Option> validateOption() {
        List<Option> listOptionReal = new ArrayList<>();
        for (Option option : mPoll.getOptions()) {
            if (option.getName() != null || option.getImage() != null) listOptionReal.add(option);
        }
        if (listOptionReal.size() == 0) {
            mView.showMessage(R.string.msg_option_blank);
            return null;
        }
        return listOptionReal;
    }

    @Override
    public void submitEditSetting() {
        mView.showProgress();
        mRepository.editPoll(PollEditionApi.TYPE_EDIT_SETTING, mPoll,
                new DataCallback<DataInfoItem>() {
                    @Override
                    public void onSuccess(DataInfoItem data) {
                        mView.hideProgress();
                        mView.showMessage(R.string.update_success);
                    }

                    @Override
                    public void onError(String msg) {
                        mView.showMessage(msg);
                    }
                });
    }

    @Override
    public void onClickNext() {
        if (mView != null) mView.nextUI();
    }

    @Override
    public void onClickPrevious() {
        if (mView != null) mView.previousUI();
    }
}
