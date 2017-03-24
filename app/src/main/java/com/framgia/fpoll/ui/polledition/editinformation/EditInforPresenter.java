package com.framgia.fpoll.ui.polledition.editinformation;

import android.text.TextUtils;

import com.framgia.fpoll.data.model.DataInfoItem;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.data.source.remote.polldatasource.PollRepository;
import com.framgia.fpoll.networking.ResponseItem;
import com.framgia.fpoll.networking.api.UpdateInfoPollService;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_MULTI;
import static com.framgia.fpoll.util.Constant.TypeChoose.TYPE_SINGER;
import static com.framgia.fpoll.util.Constant.TypeEditPoll.TYPE_EDIT_POLL;

/**
 * Created by framgia on 16/03/2017.
 */
public class EditInforPresenter implements EditInforContract.Presenter {
    private EditInforContract.View mView;
    private PollItem mPollInformation;
    private PollRepository mRepository;

    public EditInforPresenter(EditInforContract.View view, PollItem pollItem,
                              PollRepository repository) {
        mView = view;
        mPollInformation = pollItem;
        mRepository = repository;
    }

    @Override
    public void nextStep(int id) {
        mView.bindError();
        if (mView == null ||
            TextUtils.isEmpty(mPollInformation.getUser().getUsername()) ||
            TextUtils.isEmpty(mPollInformation.getUser().getEmail()) ||
            TextUtils.isEmpty(mPollInformation.getTitle()) ||
            (!android.util.Patterns.EMAIL_ADDRESS.matcher(mPollInformation.getUser().getEmail())
                .matches()))
            return;
        saveInformation(id);
    }

    @Override
    public void saveInformation(int id) {
        mView.showDialog();
        String username = mPollInformation.getName();
        String email = mPollInformation.getEmail();
        String title = mPollInformation.getTitle();
        int type = mPollInformation.isMultiple() ? TYPE_MULTI : TYPE_SINGER;
        int typeEdit = TYPE_EDIT_POLL;
        String dateClose = mPollInformation.getDateClose();
        String description = mPollInformation.getDescription();
        UpdateInfoPollService.PollInfoBody body = new UpdateInfoPollService.PollInfoBody
            (username, email, title, type, typeEdit, dateClose, description);
        mRepository.editPollInformation(id, body, new DataCallback<ResponseItem<DataInfoItem>>() {
            @Override
            public void onSuccess(ResponseItem<DataInfoItem> data) {
                mView.hideDialog();
                mView.showMessage(ActivityUtil.byString(data.getMessage()));
            }

            @Override
            public void onError(String msg) {
                mView.hideDialog();
                mView.showMessage(msg);
            }
        });
    }

    @Override
    public void back() {
        if (mView != null) mView.back();
    }

    @Override
    public void showDatePicker() {
        if (mView != null) mView.showDatePicker();
    }

    @Override
    public void showTimePicker() {
        if (mView != null) mView.showTimePicker();
    }
}
