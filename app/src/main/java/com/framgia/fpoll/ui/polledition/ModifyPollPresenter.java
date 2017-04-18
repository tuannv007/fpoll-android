package com.framgia.fpoll.ui.polledition;

import android.support.annotation.Nullable;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.networking.api.UpdatePollService;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fpoll.networking.api.UpdatePollService.EditTypeConstant.TYPE_OPTION;
import static com.framgia.fpoll.networking.api.UpdatePollService.EditTypeConstant.TYPE_SETTING;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_MULTI;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_SINGER;

/**
 * Created by framgia on 15/03/2017.
 */
public class ModifyPollPresenter implements ModifyPollContract.Presenter {
    private PollItem mPoll;
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
    public void onUpdatePoll() {
        if (mView == null || mRepository == null) return;
        mView.submitEditPoll();
    }

    @Override
    public void updateInformation() {
        mView.showProgress();
        int type = mPoll.isMultiple() ? TYPE_MULTI : TYPE_SINGER;
        UpdatePollService.PollInfoBody body =
                new UpdatePollService.PollInfoBody(mPoll.getUser().getUsername(),
                        mPoll.getUser().getEmail(), mPoll.getTitle(), type, mPoll.getDateClose(),
                        mPoll.getDescription(), mPoll.getLocation());
        mRepository.updateInformation(mPoll.getId(), body, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                onUpdateSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.hideProgress();
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void updateOption() {
        List<Option> listOptionReal = validateOption();
        if (listOptionReal == null) return;
        mPoll.setOptions(listOptionReal);
        mView.showProgress();
        mRepository.updateOptionSetting(TYPE_OPTION, mPoll, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                onUpdateSuccess(data);
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
    public void updateSetting() {
        mView.showProgress();
        mRepository.updateOptionSetting(TYPE_SETTING, mPoll, new DataCallback<DataInfoItem>() {
            @Override
            public void onSuccess(DataInfoItem data) {
                onUpdateSuccess(data);
            }

            @Override
            public void onError(String msg) {
                mView.showMessage(msg);
            }
        });
    }

    private void onUpdateSuccess(DataInfoItem data) {
        mPoll = data.getPoll();
        mView.hideProgress();
        mView.showMessage(R.string.update_success);
    }
}
